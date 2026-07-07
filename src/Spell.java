public abstract class Spell {

    protected String name;
    protected String desc;
    protected String textWhenCast;
    protected String formula;
    protected String textWhenLearned;
    protected boolean learned = false;
    protected int str;
    protected int mpCost;
    protected boolean aoe;


    public Spell() {}

    public void cast(int pickTarget) {
    }

    public void cast() {
    }

}
