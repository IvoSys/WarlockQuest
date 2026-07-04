import java.util.ArrayList;

public class Player {

    static int level;
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
        System.out.println("\n===================");
        for (Item i : inv){
            if (i instanceof ItemIngred)
                continue;
            System.out.printf("· %s \n", i.name);
        }
        System.out.println("===================\n");
    }

    public static void showIngredients(){
        boolean empty = true;
        System.out.println("\n===================");
        for (Item i : inv){
            if (i instanceof ItemIngred) {
                System.out.printf("· %s \n", i.name);
                empty = false;
            }
        }
        if (empty){
            System.out.println("Keine einzige Zutat.");
        }
        System.out.println("===================\n");
    }

    public static void showSpells(){
        boolean empty = true;
        System.out.println("\n===================");
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
        System.out.println("===================\n");
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

    }

    public static void showStatus(){
        boolean first = true;
        System.out.printf("Maleficarius [Lv. %d]: \t%d/%d MP \n", level, mp, mpMax);
        for (Demon i : team){
            if (first){
                System.out.println("----------------------------");
            }
            System.out.printf("%s: \t%d/%d HP");
            first = false;
        }
        if (first){
            System.out.println("Keine Dämonen gebunden");
        }

    }

    public static void (){
        level++;
        mp *= 1.25f;
        mpMax *= 1.25f;
    }

}
