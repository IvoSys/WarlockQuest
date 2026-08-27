import java.util.ArrayList;

public class Room {

    protected int ID;                           // ID des Raumes für Abgleich mit Schlüsseln, Puzzle-Items, Dialoge usw., von Raumkoordinate abgeleitet (nur Raum 000 hat -1)
    protected String name;                      // Name des Raums
    protected String desc;                      // Beschreibung des Raums
    protected String descB;                     // Beschreibung des Raums nach ???
    protected String descDoors;                 // Beschreibung der offenen/geschlossenen Türen
    protected String descDoorsB;                // Beschreibung der offenen/geschlossenen Türen NACH AUFSCHLIESSEN
    protected String descPostBattle;            // Beschreibung des Raumes nach gewonnenem Encounter
    protected String descSolved;                // Beschreibung des Raumes nach gelöstem Rätsel
    protected String daimon;                    // Daimon-Kommantar
    protected String daimonB;                   // Daimon-Kommnatar nach ???
    protected String daimonSolved;              // Daimon-Kommnatar nach gelöstem Rätsel
    protected String solvedText;                // Text, wenn Rätsel gelöst wird
    protected String unlockedText;              // Text, wenn Tür aufgeschlossen wird
    protected boolean PuzzleSolved = false;     // Marker, ob Rätsel gelöst
    protected boolean DoorUnlocked = false;     // Marker, ob Tür aufgeschlossen
    protected boolean north, east, south, west, up, down;   //In welche Richtungen der Raum verlassen werden kann
    protected boolean hasDialogue;              // Marker, ob Dialog vorhanden
    protected ArrayList<Item> loot = new ArrayList<>();                     // Liste von erhältlichen Objekten
    protected ArrayList<RoomObject> objects = new ArrayList<>();            // Liste von Objekten im Raum, mit denen interagiert werden kann und die daraufhin verschiedene Beschreibungen und weiteren Loot ausgeben können
    protected Item reward;                      // Nach Lösung des Rätsels erhältlicher Gegenstand, wird in Loot-Array verschoben.

    protected Encounter encounter;
    protected boolean encounterBeaten = false;

    public Room(String name, String desc, String descDoors, String daimon, boolean north, boolean east, boolean south, boolean west, boolean up, boolean down, boolean hasDialogue, int ID) {
        this.name = name;
        this.desc = desc;
        this.descDoors = descDoors;
        this.daimon = daimon;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.up = up;
        this.down = down;
        this.hasDialogue = hasDialogue;
        this.ID = ID;
    }

    public static void describe(){
        boolean first = true;
        System.out.println();
        System.out.println(Player.room.name.toUpperCase());
        System.out.print(Player.room.desc);
        for (Item i : Player.room.loot){
            if (i.descInRoom != null) {
                if (first)
                    System.out.println();
                System.out.print(i.descInRoom);
                first = false;
            }
        }
        if (!first)
            System.out.println();
        if (Player.room.descDoors != null) {
            System.out.println(Player.room.descDoors);
        }
    }

    public void unlockOrSolve(String input){
        Room room = Player.room;
        boolean found = false;
        for (Item i : Player.inv){
            if (input.contains(i.name.toLowerCase())){              // Wenn Eingabe den Namen eines Key-Items im Inventar enthält und
                if(i.ID == Player.room.ID){                         // wenn die puzzleID dieses Key-Items zu der des aktuellen Raums passt, dann

                    if (i instanceof Key){
                        if (room.descDoorsB != null)
                            room.descDoors = room.descDoorsB;
                        if (room.unlockedText != null)
                            System.out.println(room.unlockedText);
                        unlockThisRoom();                                // spezifisches Ereignis für Raum, falls vorhanden, z.B. Tür öffnet sich und bestimmter boolean wird umgeschaltet
                        if (i.isConsumed)
                            Player.inv.remove(i);

                    } else if (i instanceof PuzzleItem){
                        System.out.println(Player.room.solvedText);     // Raum gelöst! Entsprechender Text wird ausgegeben.
                        if (room.reward != null){                // Wenn es eine Belohnung gibt,
                            loot.add(room.reward);               // wird sie ins Loot-Array verschoben und kann mit n. eingesammelt werden
                            room.reward = null;
                        }
                        if (room.descSolved != null){            // Raumbeschreibung und Daimon-Kommentar werden ggf. aktualisiert
                            room.desc = room.descSolved;
                        }
                        if (room.daimonSolved != null) {
                            room.daimon = room.daimonSolved;
                        }
                        if (i.isConsumed) {                              // Wenn das Key-Item verbraucht wird, wird es aus dem Inventar gelöscht
                            Player.inv.remove(i);
                        }
                        room.PuzzleSolved = true;                 // Raum als gelöst markieren
                    }

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

    public static void unlockThisRoom(){                      //Zu nah an "Universalskript"?
        switch (Player.room.ID){                              //Individuelle Ereignisse für gelöste Räume
            case -1:                                          //Zellentür öffnet sich, Raum kann nach Osten verlassen werden
                BuildCastle.castle[0][0][0].east = true;
                Daimon.solved000Comment();
                break;
            case 12:
                BuildCastle.castle[0][1][2].up = true;
                break;
            default:
                System.out.println("DEBUG: Passende IDs, aber kein Case in unlockThisRoom() hinterlegt.");
        }
    }



}
