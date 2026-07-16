import java.util.Random;

public abstract class Demon {

    String name;
    String trueName;
    String desc;
    String textWhenSummoned;
    String textWhenBound;
    String obliterated;

    int hp;
    int hpMax;
    int hpBase;
    int power;
    int powerBase;
    int dex;
    int level;
    boolean melee;

    boolean ko = false;

    String attackName;
    String attackDesc;
    String aoeAttackName;
    String aoeAttackDesc;
    String aoeAttackBattleDesc;
    String selfBuffName;
    String selfBuffDesc;


    public Demon() {
    }

    public static void showDemons() {
        System.out.println("=============DÄMONEN==============\n");
        if (Player.team.isEmpty())
            System.out.println("Du hast noch keine Dämonen gebunden.");
        else
            for (Demon d : Player.team) {
                System.out.println(d.name.toUpperCase());
                System.out.printf("Leben: %d \t\tMacht: %d \t\tGewandheit: %d\n", d.hpMax, d.power, d.dex);
                System.out.println(d.desc);
                System.out.println();
                System.out.println("1. " + d.attackName);
                System.out.println(d.attackDesc);
                System.out.println("2. " + d.aoeAttackName);
                System.out.println(d.aoeAttackDesc);
                System.out.println("3. " + d.selfBuffName);
                System.out.println(d.selfBuffDesc);
                System.out.println("----------------------------------\n");
            }
        System.out.println("==================================");
    }

    public void toPlayerLevel() {
        level = Player.level;

        switch (level) {
            case 1:
                hpMax = hpBase;
                power = powerBase;
                break;
            case 2:
                hpMax = (int) (hpBase * 1.3f);
                hp = hpMax;
                power = (int) (powerBase * 1.3f);
                break;
            case 3:
                hpMax = (int) (hpBase * 1.6f);
                hp = hpMax;
                power = (int) (powerBase * 1.6f);
                break;
            case 4:
                hpMax = hpBase * 2;
                hp = hpMax;
                power = powerBase * 2;
                break;
            case 5:
                hpMax = (int) (hpBase * 2.5f);
                hp = hpMax;
                power = (int) (powerBase * 2.5f);
                break;
            default:
                level = 5;
                hpMax = (int) (hpBase * 2.5f);
                power = (int) (powerBase * 2.5f);
                break;
        }
    }

    public static void summon(int index) {
        Battle.demon = Player.team.get(index);
        System.out.println(Battle.demon.textWhenSummoned);
    }

    //HP-Setter
    public boolean applyDmgEvade(int dmg) {
        boolean hit = false;

        if (Battle.rnd.nextInt(100) < Battle.demon.dex)
            System.out.println(Battle.demon.name + " weicht aus!");
        else {
            hit = true;
            hp -= dmg;
            if (hp <= 0) {
                hp = 0;
                ko = true;
            }
            System.out.printf("%s erleidet %d Schaden", Battle.demon.name, dmg);
            if (ko)
                System.out.println(" und ist besiegt.");
            else
                System.out.println(".");
        }
        return hit;
    }

    public void applyDmg(int dmg) {
        hp -= dmg;
        if (hp < 0)
            hp = 0;
        if (hp == 0) {
            ko = true;
        }
        System.out.printf("%s erleidet %d Schaden", Battle.demon.name, dmg);
        if (ko)
            System.out.println(" und ist besiegt.");
        else
            System.out.println(".");
    }

    public void applyHeal(int heal) {
        hp += heal;
        if (hp > hpMax)
            hp = hpMax;
    }


    //Angriffe
    public abstract int attack();

    public abstract int aoeAttack();

    public abstract void selfBuff();

}

// ============= MINOTAURUS =============

class Minotauros extends Demon{

    Random rnd = new Random();

    protected boolean isRoar;
    protected int roar = 0;

    public Minotauros() {
        name = "Minotauros";
        trueName = Story.trueNameDem01;
        desc = "Ein aufrecht gehender Stier, bewaffnet mit einer mächtigen Streitaxt. ";
        textWhenSummoned = "Aus Fleisch, Knochen und kochendem Blut formt sich der Körper eines gehörnten Untiers. Wut lodert in seinen Augen.";
        textWhenBound = "Du sprichst den wahren Namen des Minotauros aus und die Beschwörungsformel zerreißt unter deinen Fingern. \nAus den Tiefen der Unterwelt hebt sich ein Gebrüll der reinen Wut.\n\n(Über den Befehl \"Dämonen\" erfährst du mehr über deine Dämonen.)";
        hpBase = 100;
        hpMax = hpBase;
        hp = hpMax;
        powerBase = 15;
        power = powerBase;
        dex = 10;
        melee = true;

        attackName = "Streitaxt\t\t";
        attackDesc = "Minotauros schlägt mit seiner Streitaxt zu und verursacht bei einem Gegner hohen Schaden.";
        aoeAttackName = "Wüten\t\t\t";
        aoeAttackDesc = "Minotauros verfällt in blinde Wut und verursacht bei \nallen Gegnern Schaden in unvorhersehbarer Höhe.";
        aoeAttackBattleDesc = "In Raserei schlägt Minotauros wild um sich:";
        selfBuffName = "Schreckliches Gebrüll";
        selfBuffDesc = "Minotauros brüllt fürchterlich. Bis zum nächsten Zug weicht Minotaurus keinen Angriffen aus, erhält nur halben Schaden \nund sein nächster Angriff mit der Streitaxt wird um den erlittenen Schaden erhöht. ";
        //obliterated = "wird gespalten.";  // wenn Gegner mit besonders starken Schlag besiegt wird: "Gegner erhält 27 Schaden und …"
    }

    @Override
    public boolean applyDmgEvade(int dmg){

        boolean hit = false;

        if (!isRoar) {
            super.applyDmgEvade(dmg);
        }
        else {
            dmg = (int) (dmg * 0.5f);
            hp -= dmg;
            roar += dmg;
            hit = true;
            if (hp <= 0) {
                hp = 0;
                ko = true;
            }

            System.out.printf("Minotauros erleidet %d Schaden", dmg);
            if (ko)
                System.out.println(" und ist besiegt.");
            else
                System.out.println(".");
        }
        return hit;
    }

    @Override
    public void applyDmg(int dmg){
        if (!isRoar) {
            super.applyDmg(dmg);
        }
        else {
            dmg = (int) (dmg * 0.5f);
            hp -= dmg;
            roar += dmg;
            if (hp < 0)
                hp = 0;
            if (hp == 0)
                ko = true;

            System.out.printf("Minotauros erleidet %d Schaden", dmg);
            if (ko)
                System.out.println(" und ist besiegt.");
            else
                System.out.println(".");
        }
    }

    @Override
    public int attack() {
        Random rnd = new Random();

        if (roar > 0)
            System.out.print("Von seinen Wunden in Rage versetzt, lässt Minotauros die schwere Streitaxt hinabfahren – ");
        else
            System.out.print("Mit dampfendem Atem attackiert Minotauros mit seiner Streitaxt – ");

        int dmg = rnd.nextInt(11) + power + roar;
        roar = 0;
        isRoar = false;
        return dmg;
    }

    @Override
    public int aoeAttack() {
        isRoar = false;
        return rnd.nextInt((power+1)*2);
    }

    @Override
    public void selfBuff() {
        roar = 0;
        isRoar = true;
        System.out.println("Minotauros brüllt markerschütternd, eine unmissverständliche Drohung.");
    }

}

// ============= EFREET =============

class Efreet extends Demon{

    Random rnd = new Random();

    protected boolean isBlazing = false;
    protected String aoeAttackNameBlazing;
    protected String aoeAttackBattleDescBlazing;

    public Efreet() {
        name = "Efreet";
        trueName = Story.trueNameDem02;
        desc = "Ein Geistwesen, geschaffen aus rauchlosem Feuer.";
        textWhenSummoned = "Flammen ziehen sich in einem Strudel zusammen und bilden eine Frau in roten Seidengewändern. Ihre goldenen Armreife glühen heiß.";
        textWhenBound = "Du sprichst den wahren Namen der Efreet aus und die Beschwörungsformel verbrennt unter deinen Fingern. \nEinen kurzen Moment lang glaubst du, in Flammen zu stehen.\n\n(Über den Befehl \"Dämonen\" erfährst du mehr über deine Dämonen.)";
        hpBase = 120;
        hpMax = hpBase;
        hp = hpMax;
        powerBase = 20;
        power = powerBase;
        dex = 30;
        melee = true;

        attackName = "Flammenschlag";
        attackDesc = "Ein glühender Schlag trifft einen Gegner und verursacht hohen Schaden.";
        aoeAttackName = "Feuersbrunst";
        aoeAttackNameBlazing = "Inferno";
        aoeAttackDesc = "Brüllende Flammen verusachen bei allen Gegnern Schaden in unvorhersehbarer Höhe.";
        aoeAttackBattleDesc = "Eine Walze aus Feuer überrollt Efreets Feinde:";
        aoeAttackBattleDescBlazing = "Der Raum explodiert in einem Flammenmeer:";
        selfBuffName = "Innere Glut";
        selfBuffDesc = "Eine Runde lang lodern Flammen wild um Efreet. \nIm Nahkampf angreifende Gegner verbrennen sich und der Angriff \"Feuersbrunst\" wird verstärkt.";
        //obliterated = "";
    }

    @Override
    public int attack() {
        isBlazing = false;
        return rnd.nextInt(11) + power;
    }

    @Override
    public int aoeAttack() {
        if (isBlazing) {
            isBlazing = false;
            return rnd.nextInt((power + 1) * 3);
        }
        else
            return rnd.nextInt((power+1)*2);
    }

    @Override
    public void selfBuff() {
        isBlazing = true;
    }
}

// ============= ABADDON =============

class Abaddon extends Demon{

    Random rnd = new Random();

    public Abaddon() {
        name = "Abaddon";
        trueName = Story.trueNameDem03;
        desc = "";
        textWhenSummoned = "Aus den Schatten tritt eine verhüllte Gestalt. Eine schwarze Pfütze \nwandert an ihren Füßen mit ihr, dünne Tentakel züngeln daraus hevor.";
        textWhenBound = "Du sprichst den wahren Namen des Abaddon aus und die Beschwörungsformel verwelkt unter deinen Fingern. \nDie Schatten rücken näher, knurren und greifen nach dir.\n\n(Über den Befehl \"Dämonen\" erfährst du mehr über deine Dämonen.)";
        hpBase = 150;
        hpMax = hpBase;
        hp = hpMax;
        powerBase = 25;
        power = powerBase;
        dex = 20;
        melee = false;

        attackName = "Schattenblitz";
        attackDesc = "Ein schwarzer Blitz zuckt durch einen Gegner und verursacht hohen Schaden.";
        aoeAttackName = "Dunkelheit";
        aoeAttackDesc = "Zähne und Klauen aus der Dunkelheit verusachen bei allen Gegnern Schaden in unvorhersehbarer Höhe.";
        aoeAttackBattleDesc = "Die Gegner werden verschlungen von Dunkelheit und allem, was darin lauert:";
        selfBuffName = "Finsteres Herz";
        selfBuffDesc = "[Funktionsweise Selfbuff Abaddon] Angreifende Gegner erhalten eine Zustandsveränderung?";
        //obliterated = "";
    }

    @Override
    public int attack() {
        return rnd.nextInt(11) + power;
    }

    @Override
    public int aoeAttack() {
        return rnd.nextInt((power+1)*2);
    }

    @Override
    public void selfBuff() {
    }
}





