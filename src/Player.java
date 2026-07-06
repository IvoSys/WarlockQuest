import java.util.ArrayList;

public class Player {


    static int mp = 50;
    static int mpMax = 50;

    static ArrayList<Item> inv = new ArrayList<>();
    static ArrayList<Spell> spellbook = new ArrayList<>();
    static ArrayList<Demon> team = new ArrayList<>();
    static Demon activeDemon;

    //Position
    static int curX = 0;
    static int curY = 0;
    static int curZ = 0;
    static Room room = WorldBuilder.castle[curX][curY][curZ];
    static boolean moved = true;


    public static void move(String input){
        if (input.contains("nord") || input.equals("n") && room.north){
            curY++;
            moved = true;
            System.out.println("Du gehst nach Norden.");
        } else if (input.contains("ost") || input.equals("o") && room.east){
            curX++;
            moved = true;
            System.out.println("Du gehst nach Osten.");
        } else if (input.contains("süd") || input.equals("s") && room.south){
            curY--;
            moved = true;
            System.out.println("Du gehst nach Süden.");
        } else if (input.contains("west") || input.equals("w") && room.west){
            curX--;
            moved = true;
            System.out.println("Du gehst nach Westen.");
        } else if (input.contains("hoch") || input.equals("h") || input.contains("oben") && room.up){
            curZ++;
            moved = true;
            System.out.println("Du gehst nach oben.");
        } else if (input.contains("runter") || input.equals("r") || input.contains("unten") && room.down){
            curZ--;
            moved = true;
            System.out.println("Du gehst nach unten.");
        } else {
            System.out.println("Hier geht es nicht weiter.");
        }
    }

    public static void daimon(){                                //Zu nah an "Universalskript"?
        System.out.println(room.daimon);                        //Daimon-Kommentar für diesen Raum
        if ((room.puzzleID == 0) && (room.reward != null)) {    //In bestimmten Räumen führt Daimon spezielle Methoden aus
            Story.getKey000();
        }
    }

    public static void showInv(){
        System.out.println("=============INVENTAR==============");
        for (Item i : inv){
            if (i instanceof ItemIngred)
                continue;
            System.out.printf("· %s ", i.name);
            if (i.num > 1)
                System.out.printf("[%d] \n", i.num);
            else
                System.out.print("\n");
        }
        System.out.println("===================================");
    }

    public static void showIngredients(){
        boolean empty = true;
        System.out.println("==========ALCHEMIEZUTATEN==========");
        for (Item i : inv){
            if (i instanceof ItemIngred) {
                System.out.printf("· %s [%d] \n", i.name, i.num);
                empty = false;
            }
        }
        if (empty){
            System.out.println("Keine einzige Zutat.");
        }
        System.out.println("====================================");
    }

    public static void showSpells(){
        boolean empty = true;
        System.out.println("=============ZAUBERBUCH=============");
        for (Spell i : spellbook) {
            System.out.printf("· \t%s \n", i.name);
            System.out.printf("\tMana: %d, \tStärke: %d \t", i.mpCost, i.str);
            if (i.aoe)
                System.out.print("Flächenwirkung");
            System.out.printf("\n\t%s \n", i.desc);
            empty = false;
        }
        if (empty){
            System.out.println("Kein einziger Zauber.");
        }
        System.out.println("====================================");
    }

    public static void checkItem(String input){
        boolean found = false;
        for (Item i : inv){
            if (input.contains(i.name.toLowerCase())){
                System.out.println(i.desc);
                if (i == WorldBuilder.bag)
                    showIngredients();
                else if (i == WorldBuilder.book)
                    showSpells();
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("So etwas habe ich nicht.");
    }

    public static void combineItems(String input1, String input2){
        if (input1.equals(input2)) {                // Abbruch, wenn zweimal gleiches Item eingegeben wurde
            System.out.println("Das funktioniert nur mit zwei unterschiedlichen Gegenständen.");
        } else {
            Item item1 = null;
            Item item2 = null;
            boolean successConsumeItem1 = false;
            boolean successConsumeItem2 = false;

            for (Item i : inv) {                                // Inventar durchgeben,
                if (input1.equals(i.name.toLowerCase())) {      // wenn input mit Namen eines Items genau übereinstimmt
                    item1 = i;                                  // Item in Wegwerfvariable speichern
                }
                if (input2.equals(i.name.toLowerCase())) {      // für zweiten Input auch
                    item2 = i;
                }
            }
            // Abbruch, wenn eine oder beide Variablen nicht befüllt wurden, d.h. die angegebenen Items nicht im Inventar sind
            if (item1 == null && item2 == null )
                System.out.println("Ich habe nichts davon.");
            else if (item1 == null)
                System.out.println("Den ersten Gegenstand hast du nicht.");
            else if (item2 == null)
                System.out.println("Den zweiten Gegenstand hast du nicht.");
            else {

                // Items sind im Inventar
                // Abbruch, wenn IDs unterschiedlich oder 0 sind – diese Items können nicht kombiniert werden können
                if ((item1.combiID != item2.combiID) || item1.combiID == 0) {
                    System.out.println("Diese beiden Gegenstände lassen sich nicht kombinieren.");

                // Items sind im Inventar vorhanden und können kombiniert werden.
                // Versuchen, die benötigte Anzahl anzuziehen:
                } else {
                    successConsumeItem1 = Item.consumeItemMult(item1, 1);
                    if (successConsumeItem1) {
                        successConsumeItem2 = Item.consumeItemMult(item2, 1);
                        }
                }
                // Wenn Zutaten erfolgreich verbraucht, dann Item erzeugen:
                if (successConsumeItem1 && successConsumeItem2) {
                    if (item1.combiID == 1) {               // id = 1: Heiltrank
                        Item.obtainItemMult(WorldBuilder.pot01, 1);
                        System.out.printf("Du hast einen %s gebraut. \n", WorldBuilder.pot01.name);
                    } else if (item1.combiID == 2) {        // id = 2: Manatrank
                        Item.obtainItemMult(WorldBuilder.pot02, 1);
                        System.out.printf("Du hast einen %s gebraut. \n", WorldBuilder.pot02.name);
                    }
                }
            }
        }
    }

    public static void showStatus(){
        boolean first = true;
        System.out.printf("Maleficarius: \t%d/%d MP \n", mp, mpMax);
        for (Demon i : team){
            if (first){
                System.out.println("----------------------------");
            }
            System.out.printf("%s: \t%d/%d HP \n", i.shortName, i.hp, i.hpMax);
            first = false;
        }
        if (first){
            System.out.println("Keine Dämonen gebunden");
        }

    }

    public static void cleanUpInv() {
        boolean merge;

        do {
            merge = false;
            for (int i = 0; i < Player.inv.size() - 1; i++) {
                for (int j = i + 1; j < Player.inv.size(); j++) {
                    if (Player.inv.get(i).name.equals(Player.inv.get(j).name)) {
                        Player.inv.get(i).num += Player.inv.get(j).num;
                        //Player.inv.get(j).num = 0;
                        Player.inv.remove(j);
                        merge = true;
                    }
                }
            }
        } while (merge);
        System.out.println("Inventar und Alchemiebeutel aufgeräumt.");
    }
}
