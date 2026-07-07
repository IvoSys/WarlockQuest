public abstract class Spell {

    protected String name;
    protected String desc;
    protected String formula;
    protected String textWhenLearned;
    protected int mpCost;
    protected int str;
    protected boolean aoe;

    public Spell() {}

    public void cast(int pickTarget) {
    }

    public void cast() {
    }

}
