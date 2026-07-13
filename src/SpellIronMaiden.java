public class SpellIronMaiden extends Spell {

    public SpellIronMaiden() {
        name = "Eiserne Jungfrau";
        desc = "Verflucht alle Gegner, sodass sie sich selbst verletzen, wenn sie deinem Dämon Schaden zufügen.";
        formula = Story.formulaIronMaiden;
        textWhenLearned = "";
        str = Player.spellpower;
        dur = 5;
        mpCost = 10;
        aoe = false;
    }

    public void cast(int enemyIndex) {
        Enemy target = Player.room.encounter.enemyTeam.get(enemyIndex);

        if (Player.applyMpCost(mpCost)) {
            target.inIronMaiden = true;
            target.counterIronMaiden = dur;
            System.out.printf("Maleficarius belegt %s mit dem Fluch \"%s\". \n", target.name, name);

        } else {
            System.out.println("Der Zauber schlägt fehl – Maleficarius verfügt nicht über ausreichend Mana.");
        }
    }

    @Override
    public void tick(Enemy e) {
        e.counterIronMaiden--;
        if (e.counterIronMaiden <= 0) {
            e.inIronMaiden = false;
            if (!e.ko)
                System.out.printf("Der Fluch \"%s\" auf %s klingt ab. \n", name, e.name);
        }
    }



}
