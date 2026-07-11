public class ItemEvoc extends Item {

    String demonShortName;

    public ItemEvoc(String scrollTitle, String nameVague, String desc, String demonShortName) {
        name = scrollTitle;                     //Name der Beschwörungsformel
        this.nameVague = nameVague;
        this.desc = desc;                       //Text mit Rätsel zum trueName des Dämonen
        this.demonShortName = demonShortName;   //Kann mit name-Variablen des Dämonen abgeglichen werden
    }


    public static void bind() {
        boolean success = false;
        System.out.print("Du machst dich bereit, einen neuen Dämon zu versklaven. \nGib seinen wahren Namen ein, und sei genau! \n> ");
        WarlockQuest.input = WarlockQuest.sc.nextLine().trim();                     // Nutzer gibt ein, was er für wahren Namen eines Dämonen hält.
        for (int i = 0; i < WorldBuilder.freeDem.size(); i++) {                     // Liste durchgehen und
            if (WorldBuilder.freeDem.get(i).trueName.equals(WarlockQuest.input)) {  // Dämon finden, dessen trueName mit der Eingabe übereinstimmt.
                Demon toBind = WorldBuilder.freeDem.get(i);                         // Treffer für Lesbarkeit in temp-Variablen speichern.

                if (Player.team.contains(toBind)) {                                 // Prüfen, ob dieser Dämon bereits gebunden wurde.
                    System.out.println("Diesen Dämon hast du bereits gebunden.");

                } else {                                                            // Wenn nicht, dann
                    for (Item j : Player.inv) {                                     // Inventar durchgehen und prüfen,
                        if (((ItemEvoc) j).demonShortName.equals(toBind.name)) {    // ob Beschwörungsformel für diesen Dämon in Besitz ist
                            Player.inv.remove(j);                                   // Wenn ja, kann Formel vernichtet und Dämon gebunden werden.
                            System.out.println("Die Beschwörungsformel verbrennt unter deinen Fingern.");
                            toBind.toPlayerLevel();                                 // Dämon-Stufe anpassen und …
                            Player.team.add(toBind);                                // endlich ins Team aufnehmen!
                            System.out.println(toBind.textWhenBound);
                            //WorldBuilder.freeDem.remove(j);                       // Eintrag nicht löschen, damit später noch abgeglichen werden kann für "bereits gebunden".
                            success = true;                                         // Den Prozess als erfolgreich kennzeichnen und Schleife abbrechen.
                            break;
                        }
                    }
                }
            }
        }
        if (!success)                   // Wenn das Ganze nicht funktioniert hat, anderen Text ausgeben
            System.out.println("Hämisches Gelächter hämmert in deinem Schädel hin und her wie der Klöppel eine Glocke. \nDieses Mal hast du keinen neuen Diener erhalten.");
    }

}
