public class SpellLifeline extends Spell {

    public SpellLifeline(String name, String desc, String formula, String textWhenLearned, int str, int mpCost, boolean aoe) {
        this.name = name;
        this.desc = desc;
        this.formula = formula;
        this.textWhenLearned = textWhenLearned;
        this.str = str;
        this.mpCost = mpCost;
        this.aoe = aoe;
    }

    @Override
    public void cast(int pickTarget) {

    }

}
