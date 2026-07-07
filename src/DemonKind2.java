public class DemonKind2 extends Demon{

    protected int dex = 10;

    public DemonKind2 (String name, String trueName, String textWhenSummoned, String textWhenBound) {
        this.name = name;
        this.trueName = trueName;
        this.textWhenSummoned = textWhenSummoned;
        this.textWhenBound = textWhenBound;
    }

    public int attack() {
        int dmg = 10;
        return dmg;
    }

    public int ability1() {
        int dmg = 10;
        return dmg;
    }

    public int ability2() {
        int dmg = 10;
        return dmg;
    }
}
