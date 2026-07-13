public class DemonKind3 extends Demon{

    public DemonKind3() {
        name = "Abaddon";
        trueName = Story.trueNameDem03;
        desc = "";
        textWhenSummoned = "";
        textWhenBound = "";
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
