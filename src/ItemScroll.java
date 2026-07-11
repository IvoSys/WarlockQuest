public class ItemScroll extends Item {

    String nameSpell;

    public ItemScroll(String name, String nameVague, String desc, String nameSpell) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.nameSpell = nameSpell;
    }

    public static void learn() {
        boolean success = false;
        System.out.print("Du machst dich bereit, einen neuen Zauber zu erlernen. \nGib die Zauberformel ein, und sei genau! \n> ");
        WarlockQuest.input = WarlockQuest.sc.nextLine().trim();                     // Nutzer gibt ein, was er für Formel eines Zaubers hält.
        for (int i = 0; i < WorldBuilder.freeSpells.size(); i++) {                  // Liste durchgehen und
            if (WorldBuilder.freeSpells.get(i).formula.equals(WarlockQuest.input)) { // Zauber finden, dessen formula mit der Eingabe übereinstimmt.
                Spell toLearn = WorldBuilder.freeSpells.get(i);                     // Treffer für Lesbarkeit in temp-Variablen speichern.

                if (Player.spellbook.contains(toLearn)) {                           // Prüfen, ob dieser Zauber bereits erlernt wurde.
                    System.out.println("Diesen Zauber beherrschst du bereits.");

                } else {                                                            // Wenn nicht, dann
                    for (Item j : Player.inv) {                                     // Inventar durchgehen und prüfen,
                        if (((ItemScroll) j).nameSpell.equals(toLearn.name)) {      // ob Schriftrolle für diesen Zauber in Besitz ist
                            Player.inv.remove(j);                                   // Wenn ja, kann Formel vernichtet und Zauber erlernt werden.
                            System.out.println("Die Zauberschriftrolle verbrennt unter deinen Fingern.");
                            Player.spellbook.add(toLearn);
                            System.out.println(toLearn.textWhenLearned);
                            //WorldBuilder.freeSpells.remove(i);                    // Eintrag nicht löschen, damit später noch abgeglichen werden kann für "beherrschst du bereits".
                            success = true;                                         // Den Prozess als erfolgreich kennzeichnen und Schleife abbrechen.
                            break;
                        }
                    }
                }
            }
        }
        if (!success)                       // Wenn das Ganze nicht funktioniert hat, anderen Text ausgeben
            System.out.println("Dir raucht der Kopf vor Anstrengung. \nDiesen Zauber verstehst du einfach nicht!");
    }

}
