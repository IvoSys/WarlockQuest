import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {

    protected String name;
    protected String desc;
    protected String descB;
    protected String descDoors;
    protected String descDoorsB;
    protected String descPostBattle;
    protected String descSolved;
    protected String daimon;
    protected String daimonB;
    protected String daimonC;
    protected String daimonSolved;
    protected String solvedText;
    protected boolean PuzzleSolved = false;
    protected boolean DoorUnlocked = false;
    protected boolean north, east, south, west, up, down;   //In welche Richtungen der Raum verlassen werden kann
    protected boolean hasDialogue;
    protected ArrayList<Item> loot = new ArrayList<>();
    protected Map<String,String> notLoot = new HashMap<>();
    protected Map<String,String> viewable = new HashMap<>();                // VERALTET, wird durch Liste objects ersetzt
    protected ArrayList<RoomObject> objects = new ArrayList<>();            // Liste von Objektenim Raum, die Items enthalten und unterschiedlich davon, ob Item bereits gelottet urde, verschiedene Beschreibungen ausgeben können.
    protected Item reward;                                  //Nach Lösung des Rätsels erhältlicher Gegenstand, wird in Loot-Array verschoben.
    protected int ID;                       //ID des Raumes für Abgleich mit Schlüsseln, Puzzle-Items, Dialoge usw., von Raumkoordinate abgeleitet (nur Raum 000 hat -1)

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
        System.out.println();
        System.out.println(Player.room.name.toUpperCase());
        System.out.print(Player.room.desc);
        for (Item i : Player.room.loot){
            if (i.DescInRoom != null)
                System.out.print(i.DescInRoom);
        }
        System.out.println();
        System.out.println(Player.room.descDoors);
    }

    public void loot(String input){
        boolean received = false;
        boolean lootExists = false;

        if (!loot.isEmpty()) {                      // In Raum suchen
            for (Item i : loot) {
                if (input.contains(i.name.toLowerCase()) || input.contains(i.nameVague.toLowerCase())) {
                    if (i instanceof Spellscroll) {
                        System.out.println("Du erhältst die " + i.name);
                        Spell.learnOnTake(((Spellscroll) i).nameSpell);
                    } else {
                        Player.inv.add(i);
                        System.out.printf("Du nimmst an dich: %s \n", i.name);
                    }
                    loot.remove(i);
                    received = true;
                    break;
                }
            }
        }

        if (!Player.room.objects.isEmpty()){        // In Raumobjekten suchen
            for (RoomObject o : Player.room.objects){
                if (o.loot != null && (o.loot.name.toLowerCase().equals(input) || o.loot.nameVague.toLowerCase().equals(input) )) {
                    if (o.loot instanceof Spellscroll) {
                        System.out.println("Du erhältst die " + o.loot.name);
                        Spell.learnOnTake(((Spellscroll) o.loot).nameSpell);
                    } else {
                        Player.inv.add(o.loot);
                        System.out.printf("Du nimmst an dich: %s \n", o.loot.name);
                    }
                    o.loot = null;
                    received = true;
                    break;
                }
            }
        }

        if (!received) {                                // Wenn nichts erhalten, notLoot-Hashmap prüfen
            if (notLoot.get(input) != null) {
                System.out.println(notLoot.get(input));
                received = true;

            } else if (!loot.isEmpty()) {             // Wenn auch hier nichts, prüfen, ob grundsätzlich Loot vorhanden
                lootExists = true;
            } else {
                for (RoomObject o : Player.room.objects) {
                    if (o.loot != null) {
                        lootExists = true;
                        break;
                    }
                }
            }
        }

        if (!received && !lootExists)                                  // Wenn gar kein Loot vorhanden, Hinweis ausgeben.
            System.out.println("Hier gibt es nichts zu holen");
        else if (!received)                               // Wenn Loot vorhanden, aber nichts erhalten wurde
            System.out.println("Leider nicht.");
    }

    public void unlockOrSolve(String input){
        boolean found = false;
        for (Item i : Player.inv){
            if (input.contains(i.name.toLowerCase())){              // Wenn Eingabe den Namen eines Key-Items im Inventar enthält und
                if(i.ID == Player.room.ID){                         // wenn die puzzleID dieses Key-Items zu der des aktuellen Raums passt, dann

                    if (i instanceof Key){
                        unlockThisRoom();                                // spezifisches Ereignis für Raum, falls vorhanden, z.B. Tür öffnet sich und bestimmter boolean wird umgeschaltet

                    } else if (i instanceof PuzzleItem){
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
                        Player.room.PuzzleSolved = true;                 // Raum als gelöst markieren
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
        if (Player.room.ID == -1) {                           //Individuelle Ereignisse für gelöste Räume
            WorldBuilder.castle[0][0][0].east = true;         //Zellentür öffnet sich, Raum kann nach Osten verlassen werden
            Daimon.solved000Comment();
        }
        if (Player.room.ID == 12) {
            WorldBuilder.castle[0][1][2].up = true;
        }
    }



}
