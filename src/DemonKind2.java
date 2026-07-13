public class DemonKind2 extends Demon{

    public DemonKind2 () {
        name = "Efreet";
        trueName = Story.trueNameDem02;
        desc = "";
        textWhenSummoned = "";
        textWhenBound = "";
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
