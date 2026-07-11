public class SpellIronMaiden extends Spell {

    public SpellIronMaiden() {
        name = "Eiserne Jungfrau";
        desc = "Verflucht alle Gegner, sodass sie sich selbst verletzen, wenn sie deinem Dämon Schaden zufügen.";
        formula = Story.formulaIronMaiden;
        textWhenLearned = "";
        str = Player.spellpower;
        dur = 3;
        mpCost = 10;
        aoe = false;
    }

}
