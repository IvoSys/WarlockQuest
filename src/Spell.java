public class Spell {

    protected String name;
    protected String desc;
    protected String formula;
    protected String textWhenLearned;
    protected int mpCost;
    protected int str;
    protected boolean aoe;

    public Spell(String name, String desc, String formula, String textWhenLearned, int mpCost, int str, boolean aoe) {
        this.name = name;
        this.desc = desc;
        this.formula = formula;
        this.textWhenLearned = textWhenLearned;
        this.mpCost = mpCost;
        this.str = str;
        this.aoe = aoe;
    }

    public int cast(){
        int dmg = str;
        System.out.println("Zauber macht bumms.");
        return dmg;
    }
}
