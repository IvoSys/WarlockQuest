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
                System.out.print(d.name.toUpperCase());
                System.out.printf("\t\t\t(%d HP)", d.hpMax);
                System.out.println(d.desc);
                System.out.println("-----------------------------------");
                System.out.println("Angriff: " + d.attackName);
                System.out.println(d.attackDesc);
                System.out.println("Fähigkeit 1: " + d.aoeAttackName);
                System.out.println(d.aoeAttackDesc);
                System.out.println("Fähigkeit 2: " + d.selfBuffName);
                System.out.println(d.selfBuffDesc);
                System.out.println();
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


    //Setter
    public void applyDmgEvade(int dmg) {
        if (Battle.rnd.nextInt(100) < Battle.demon.dex)
            System.out.println(Battle.demon.name + " weicht aus!");
        else {
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
        textWhenBound = "Du sprichst den wahren Namen des Minotauros aus. \nAus den Tiefen der Unterwelt hebt sich ein Gebrüll der reinen Wut.";
        hpBase = 100;
        hpMax = hpBase;
        hp = hpMax;
        powerBase = 15;
        power = powerBase;
        dex = 10;

        attackName = "Streitaxt\t\t";
        attackDesc = "Minotauros schlägt mit seiner Streitaxt zu und verursacht bei einem Gegner massiven Schaden.";
        aoeAttackName = "Wüten\t\t\t";
        aoeAttackDesc = "Minotauros verfällt in blinde Wut und verursacht bei \nallen Gegnern Schaden in unvorhersehbarer Höhe.";
        aoeAttackBattleDesc = "In Raserei schlägt Minotauros wild um sich.";
        selfBuffName = "Schreckliches Gebrüll";
        selfBuffDesc = "Minotauros brüllt fürchterlich. Bis zum nächsten Zug weicht Minotaurus keinen Angriffen aus, erhält nur halben Schaden \nund sein nächster Angriff mit der Streitaxt wird um den erlittenen Schaden erhöht. ";
        //obliterated = "wird gespalten.";  // wenn Gegner mit besonders starken Schlag besiegt wird: "Gegner erhält 27 Schaden und …"
    }

    @Override
    public void applyDmgEvade(int dmg){
        if (!isRoar) {
            super.applyDmgEvade(dmg);
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
    public void applyDmg(int dmg){
        if (!isRoar) {
            super.applyDmgEvade(dmg);
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

    public int attack() {
        Random rnd = new Random();

        if (roar > 0)
            System.out.println("Von seinen Wunden in Rage versetzt, lässt Minotauros die schwere Streitaxt hinabfahren.");
        else
            System.out.println("Mit dampfendem Atem attackiert Minotauros mit seiner Streitaxt.");

        int dmg = rnd.nextInt(11) + power + roar;
        roar = 0;
        isRoar = false;

        return dmg;
    }

    public int aoeAttack() {
        isRoar = false;
        return rnd.nextInt(power*2);
    }

    public void selfBuff() {
        isRoar = true;
        System.out.println("Minotauros brüllt markerschütternd, eine unmissverständliche Drohung.");
    }

}

// ============= EFREET =============

class Efreet extends Demon{

    public Efreet() {
        name = "Efreet";
        trueName = Story.trueNameDem02;
        desc = "";
        textWhenSummoned = "";
        textWhenBound = "Du sprichst den wahren Namen der Efreet aus. \nEinen kurzen Moment glaubst du, in Flammen zu stehen.";
        hpBase = 120;
        hpMax = hpBase;
        hp = hpMax;
        powerBase = 20;
        power = powerBase;
        dex = 30;

        attackName = "Angriff";
        attackDesc = "";
        aoeAttackName = "Fähigkeit 1";
        aoeAttackDesc = "";
        selfBuffName = "Fähigkeit 2";
        selfBuffDesc = "";
        //obliterated = "";
    }

    public int attack() {
        int dmg = power;
        return dmg;
    }

    public int aoeAttack() {
        int dmg = power;
        return dmg;
    }

    public void selfBuff() {
    }
}

// ============= ABADDON =============

class Abaddon extends Demon{

    public Abaddon() {
        name = "Abaddon";
        trueName = Story.trueNameDem03;
        desc = "";
        textWhenSummoned = "";
        textWhenBound = "Du sprichst den wahren Namen des Abaddon aus. \nDie Schatten rücken näher. Sie greifen nach dir.";
        hpBase = 150;
        hpMax = hpBase;
        hp = hpMax;
        powerBase = 30;
        power = powerBase;
        dex = 20;

        attackName = "Angriff";
        attackDesc = "";
        aoeAttackName = "Fähigkeit 1";
        aoeAttackDesc = "";
        selfBuffName = "Fähigkeit 2";
        selfBuffDesc = "";
        obliterated = "";
    }

    public int attack() {
        int dmg = power;
        return dmg;
    }

    public int aoeAttack() {
        int dmg = power;
        return dmg;
    }

    public void selfBuff() {
    }
}





