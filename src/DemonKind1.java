import java.util.Random;

public class DemonKind1 extends Demon{

    Random rnd = new Random();

    protected boolean isRoar;
    protected int roar = 0;

    public DemonKind1() {
        name = "Minotauros";
        trueName = Story.trueNameDem01;
        desc = "Ein aufrecht gehender Stier, bewaffnet mit einer mächtigen Streitaxt. ";
        textWhenSummoned = "Aus Fleisch, Knochen und kochendem Blut formt sich der Körper eines gehörnten Untiers. Wut lodert in seinen Augen.";
        textWhenBound = "Du sprichst den wahren Namen des Minotauros aus. Aus den Tiefen der Unterwelt hebt sich ein Gebrüll der reinen Wut.";
        hpBase = 100;
        hpMax = hpBase;
        hp = hpMax;
        powerBase = 10;
        power = powerBase;
        dex = 10;

        attackName = "Streitaxt\t\t";
        attackDesc = "Minotauros schlägt mit seiner Streitaxt zu und verursacht bei einem Gegner massiven Schaden.";
        aoeAttackName = "Wüten\t\t\t";
        aoeAttackDesc = "Minotauros verfällt in blinde Wut und verursacht bei \nallen Gegnern Schaden in unvorhersehbarer Höhe.";
        aoeAttackBattleDesc = "In Raserei schlägt Minotauros wild um sich.";
        selfBuffName = "Schreckliches Gebrüll";
        selfBuffDesc = "Minotauros brüllt fürchterlich. Bis zum nächsten Zug erhält er nur halben Schaden \nund sein nächster Angriff mit der Streitaxt wird um den erlittenen Schaden erhöht. ";
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
        }
        System.out.printf("Minotauros erleidet %d Schaden", dmg);
        if (ko)
            System.out.println(" und ist besiegt.");
        else
            System.out.println(".");
    }

    public int attack() {
        Random rnd = new Random();

        int dmg = rnd.nextInt(11) + power + roar;
        roar = 0;
        isRoar = false;

        System.out.println("Mit dampfendem Atem attackiert Minotauros mit seiner Streitaxt.");

        return dmg;
    }

    public int aoeAttack() {
        isRoar = false;
        return rnd.nextInt(power*2);
    }

    public void selfBuff() {
        isRoar = true;
        System.out.println("Minotauros brüllt markerschütternd, Geifer tropft dampfend zu Boden.");
    }

}
