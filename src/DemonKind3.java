public class DemonKind3 extends Demon{

    public DemonKind3() {
        name = "";
        trueName = Story.trueNameDem03;
        desc = "";
        textWhenSummoned = "";
        textWhenBound = "";
        hp = 100;
        hpMax = hp;
        power = 10;
        dex = 10;

        attackName = "Angriff";
        attackDesc = "";
        ability1Name = "Fähigkeit 1";
        ability1Desc = "";
        ability2Name = "Fähigkeit 2";
        ability2Desc = "";
    }

    public int attack() {
        int dmg = power;
        return dmg;
    }

    public int ability1() {
        int dmg = power;
        return dmg;
    }

    public int ability2() {
        int dmg = power;
        return dmg;
    }
}
