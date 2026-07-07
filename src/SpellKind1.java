public class SpellKind1 extends Spell{

    public SpellKind1 (String name, String desc, String formula, String textWhenLearned, int mpCost, int str, boolean aoe) {
        this.name = name;
        this.desc = desc;
        this.formula = formula;
        this.textWhenLearned = textWhenLearned;
        this.mpCost = mpCost;
        this.str = str;
        this.aoe = aoe;
    }

    @Override
    public void cast(int pickTarget) {
        System.out.println("Zauber macht bumm");
    }

}
