public class Room {

    protected String name;
    protected String desc;
    protected String descSolved;
    protected String daimon;
    protected String daimonSolved;
    protected String solvedText;
    protected boolean solved = false;
    protected boolean north, east, south, west, up, down;   //In welche Richtungen der Raum verlassen werden kann
    protected Item loot;                                    //Offen einsammelbarer Gegenstand
    protected String dummyLoot;                             //Erwähnte, aber nicht erhältliche Items, gibt passendes Feedback, mehrere Einträge möglich
    protected String dummyFeedback = "Das lasse ich lieber hier.";
    protected Item reward;                                  //Nach Lösung des Rätsels erhältlicher Gegenstand
    protected int puzzleID;                                 //ID zum Lösen des Rätsels eines Raums, muss mit Key-Item oder in Daimon-Methode übereinstimmen, von Raumkoordinate abgeleitet
    protected Encounter encounter;
    protected boolean encounterBeaten;

    public Room(String name, String desc, String daimon, String solvedText, boolean north, boolean east, boolean south, boolean west, boolean up, boolean down, int puzzleID) {
        this.name = name;
        this.desc = desc;
        this.daimon = daimon;
        this.solvedText = solvedText;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.up = up;
        this.down = down;
        this.puzzleID = puzzleID;
    }

    public static void describe(){
        System.out.println();
        System.out.println(Player.room.name.toUpperCase());
        System.out.println(Player.room.desc);
    }

    public void loot(String input) {
        if (loot != null){                                                  // Spieler gibt ein "nimm.[Item]"
            if(input.contains(loot.name.toLowerCase())){                    // Wenn Item offen als Loot verfügbar -> Erfolg
                Player.inv.add(loot);
                System.out.printf("Du nimmst an dich: %s \n", loot.name);
                loot = null;
            } else if (dummyLoot.toLowerCase().contains(input) && input.length() > 2) {         //Wenn es einen oder mehrere Gegenstände gibt, die für Loot gehalten werden könnten, aber nicht sind,
                System.out.println(dummyFeedback);                                              //Erhält Spieler entsprechendes Feedback. Spieler muss mindestens drei Buchstaben eingeben,
            } else {                                                                            //damit Spieler nicht durch eingabe einzelner Buchstaben mogeln kann
                System.out.println("Leider nicht.");
            }
        } else if (dummyLoot.toLowerCase().contains(input) && input.length() > 2) {
            System.out.println(dummyFeedback);
        } else {
            System.out.println("Hier ist nichts zu holen.");
        }
    }

    public static void solve(String input){
        boolean found = false;
        for (Item i : Player.inv){
            if (input.contains(i.name.toLowerCase())){              // Wenn Eingabe den Namen eines Key-Items im Inventar enthält und
                if(i.puzzleID == Player.room.puzzleID){             // wenn die puzzleID dieses Key-Items zu der des aktuellen Raums passt, dann
                    System.out.println(Player.room.solvedText);     // Raum gelöst! Entsprechender Text wird ausgegeben.
                    if (Player.room.reward != null){                // Wenn es eine Belohnung gibt, wird sie ins Inventar verschoben
                        Player.inv.add(Player.room.reward);
                        Player.room.reward = null;
                    }
                    if (Player.room.descSolved != null){            // Raumbeschreibung und Daimon-Kommentar werden ggf. aktualisiert
                        Player.room.desc = Player.room.descSolved;
                    }
                    if (Player.room.daimonSolved != null) {
                        Player.room.daimon = Player.room.daimonSolved;
                    }
                    if (i.isConsumed) {                              // Wenn das Key-Item verbraucht wird, wird es aus dem Inventar gelöscht
                        Player.inv.remove(i);
                    }
                    solveThisRoom();                                 // spezifisches Ereignis für Raum, falls vorhanden, z.B. Tür öffnet sich und bestimmter boolean wird umgeschaltet
                    Player.room.solved = true;                       // Raum als gelöst markieren
                } else {
                    System.out.println("Damit kann ich hier nichts anfangen.");
                }
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("So etwas habe ich nicht.");
    }

    public static void solveThisRoom(){                       //Zu nah an "Universalskript"?
        if (Player.room.puzzleID == 0) {                      //Individuelle Ereignisse für gelöste Räume
            WorldBuilder.castle[0][0][0].east = true;         //Zellentür öffnet sich, Raum kann nach Osten verlassen werden
        }
    }

}
