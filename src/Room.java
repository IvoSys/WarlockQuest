import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {

    protected String name;
    protected String desc;
    protected String descB;
    protected String descC;
    protected String descSolved;
    protected String daimon;
    protected String daimonB;
    protected String daimonC;
    protected String daimonSolved;
    protected String solvedText;
    protected boolean solved = false;
    protected boolean north, east, south, west, up, down;   //In welche Richtungen der Raum verlassen werden kann
    protected boolean hasDialogue;
    protected ArrayList<Item> loot = new ArrayList<>();
    protected Map<String,String> notLoot = new HashMap<>();
    protected Item reward;                                  //Nach Lösung des Rätsels erhältlicher Gegenstand, wird in Loot-Array verschoben.
    protected int puzzleID;                                 //ID zum Lösen des Rätsels eines Raums, muss mit Key-Item oder in Daimon-Methode übereinstimmen, von Raumkoordinate abgeleitet
    protected Encounter encounter;
    protected boolean encounterBeaten = false;

    public Room(String name, String desc, String daimon, boolean north, boolean east, boolean south, boolean west, boolean up, boolean down, boolean hasDialogue, int puzzleID) {
        this.name = name;
        this.desc = desc;
        this.daimon = daimon;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.up = up;
        this.down = down;
        this.hasDialogue = hasDialogue;
        this.puzzleID = puzzleID;
    }

    public static void describe(){
        System.out.println();
        System.out.println(Player.room.name.toUpperCase());
        System.out.println(Player.room.desc);
        for (Item i : Player.room.loot){
            if (i.addRoomDesc != null)
                System.out.println(i.addRoomDesc);
        }
    }

    public void loot(String input){
        boolean found = false;

        if (!loot.isEmpty()){
            for (Item i : loot){
                if (input.contains(i.name.toLowerCase()) || input.contains(i.nameVague.toLowerCase())){
                    Player.inv.add(i);
                    System.out.printf("Du nimmst an dich: %s \n", i.name);
                    loot.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found)
                if (notLoot.get(input) != null) {
                    System.out.println(notLoot.get(input));
                    found = true;
                }
            if (!found)
                System.out.println("Leider nicht.");

        } else if (notLoot.get(input) != null) {
            System.out.println(notLoot.get(input));
            found = true;
        } else
            System.out.println("Hier ist nichts zu holen.");
    }

    public void solve(String input){
        boolean found = false;
        for (Item i : Player.inv){
            if (input.contains(i.name.toLowerCase())){              // Wenn Eingabe den Namen eines Key-Items im Inventar enthält und
                if(i.puzzleID == Player.room.puzzleID){             // wenn die puzzleID dieses Key-Items zu der des aktuellen Raums passt, dann
                    System.out.println(Player.room.solvedText);     // Raum gelöst! Entsprechender Text wird ausgegeben.
                    if (Player.room.reward != null){                // Wenn es eine Belohnung gibt,
                        loot.add(Player.room.reward);               // wird sie ins Loot-Array verschoben und kann mit n. eingesammelt werden
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
        if (Player.room.puzzleID == -1) {                     //Individuelle Ereignisse für gelöste Räume
            WorldBuilder.castle[0][0][0].east = true;         //Zellentür öffnet sich, Raum kann nach Osten verlassen werden
            Daimon.solved000Comment();
        }
        if (Player.room.puzzleID == 12) {
            WorldBuilder.castle[0][1][2].up = true;
        }
    }

}
