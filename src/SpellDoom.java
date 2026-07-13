public class SpellDoom extends Spell {

    public SpellDoom() {
        name = "Untergang";
        desc = "Verflucht einen Gegner, sodass er über mehrere Runden hinweg stetig ansteigenden Schaden erleidet.";
        formula = Story.formulaDoom;
        textWhenLearned = "";
        str = Player.spellpower;
        dur = 4;
        mpCost = 20;
        aoe = false;
    }

    public void cast(int enemyIndex) {
        Enemy target = Player.room.encounter.enemyTeam.get(enemyIndex);

        if (Player.applyMpCost(mpCost)) {
            target.doomed = true;
            target.counterDoom = dur;
            System.out.printf("Maleficarius belegt %s mit dem Fluch \"%s\". \n", target.name, name);

        } else {
            System.out.println("Der Zauber schlägt fehl – Maleficarius verfügt nicht über ausreichend Mana.");
        }
    }

    @Override
    public void tick(Enemy e) {
        e.counterDoom--;
        System.out.printf("Der Fluch \"%s\" wirkt: ", name);
        e.applyDmg(str * (dur - e.counterDoom));        // Tick 1: 10; Tick 2: 20; Tick 3: 30; Tick 4: 40
        if (e.counterDoom <= 0) {
            e.doomed = false;
            if (!e.ko)
                System.out.printf("%s hat den Zauber \"%s\" überlebt. \n", e.name, name);
        }
    }



}
