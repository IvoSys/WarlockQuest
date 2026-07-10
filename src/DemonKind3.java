public class DemonKind3 extends Demon{

    public DemonKind3() {
        name = "";
        trueName = Story.trueNameDem03;
        desc = "";
        textWhenSummoned = "";
        textWhenBound = "";
        hpBase = 100;
        hpMax = hpBase;
        hp = hpMax;
        powerBase = 10;
        power = powerBase;
        dex = 10;

        attackName = "Angriff";
        attackDesc = "";
        aoeAttackName = "Fähigkeit 1";
        aoeAttackDesc = "";
        selfBuffName = "Fähigkeit 2";
        selfBuffDesc = "";
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
