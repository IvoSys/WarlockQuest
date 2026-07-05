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
        System.out.println("=============INVENTAR=============");
        for (Item i : inv){
            if (i instanceof ItemIngred)
                continue;
            System.out.printf("· %s ", i.name);
            if (i.num > 1)
                System.out.printf("[%d] \n", i.num);
            else
                System.out.print("\n");
        }
        System.out.println("==================================");
    }

    public static void showIngredients(){
        boolean empty = true;
        System.out.println("==========ALCHEMIEZUTATEN==========");
        for (Item i : inv){
            if (i instanceof ItemIngred) {
                System.out.printf("· %s ", i.name);
                if (i.num > 1)
                    System.out.printf("[%d] \n", i.num);
                else
                    System.out.print("\n");
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
        if (input1.equals(input2)) {                //Abbruch, wenn zweimal gleiches Item eingegeben wurde
            System.out.println("Das funktioniert nur mit zwei unterschiedlichen Gegenständen.");
        } else {
            boolean sucess = false;
            int id1 = 404;                          //Standardwert für "nicht gefunden"
            int id2 = 404;

            for (Item i : inv) {                                //Inventar durchgeben,
                if (input1.equals(i.name.toLowerCase())) {      //wenn input mit Namen eines Items genau übereinstimmt
                    id1 = i.combiID;                            //ID mit dessen combiID überschreiben
                }
                if (input2.equals(i.name.toLowerCase())) {
                    id2 = i.combiID;
                }
            }
            if (id1 == 404 && id2 == 404)                       //Abbruch, wenn eine oder beide IDs nicht überschrieben wurden
                System.out.println("Ich habe nichts davon.");
            else if (id1 == 404)
                System.out.println("Den ersten Gegenstand hast du nicht.");
            else if (id2 == 404)
                System.out.println("Den zweiten Gegenstand hast du nicht.");
            else {
                if (id1 != id2) {                                 //Abbruch, wenn IDs nicht gleich
                    System.out.println("Diese beiden Gegenstände lassen sich nicht kombinieren.");
                } else if (id1 == 1) {                               //Wenn ID übereinstimmt, wird ein passendes Item erzeugt: Heiltrank
                    if (Player.inv.contains(WorldBuilder.pot01))
                        Player.inv.get(WorldBuilder.pot01.num++);
                    else
                        Player.inv.add(WorldBuilder.pot01);
                    System.out.printf("Du hast einen %s gebraut. \n", WorldBuilder.pot01.name);
                    sucess = true;                                // Erfolg erst kennzeichnen, wenn wirklich ein Item erzeugt wurde.
                }

                if (sucess) {                                     // Wenn Erfolg, dann verwendete Items reduzieren bzw. entfernen.
                    for (Item i : inv) {
                        if (input1.equals(i.name.toLowerCase())) {
                            if (i.num > 1)
                                i.num--;
                            else
                                Player.inv.remove(i);
                        }
                        if (input2.equals(i.name.toLowerCase())) {
                            if (i.num > 1)
                                i.num--;
                            else
                                Player.inv.remove(i);
                        }
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

}
