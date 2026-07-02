import java.util.ArrayList;
import java.util.Locale;

public class Player {

    static ArrayList<Item> inv = new ArrayList<>();
    static ArrayList<Demon> team = new ArrayList<>();
    static Demon activeDemon;

    //Position
    static int curX = 0;
    static int curY = 0;
    static int curZ = 0;
    static Room room = WarlockQuest.castle[curX][curY][curZ];
    static boolean moved = true;

    public static void move(String direction){
        if (direction.contains("nord") && room.north){
            curY++;
            moved = true;
            System.out.println("Du gehst nach Norden.");
        } else if (direction.contains("ost") && room.east){
            curX++;
            moved = true;
            System.out.println("Du gehst nach Osten.");
        } else if (direction.contains("süd") && room.south){
            curY--;
            moved = true;
            System.out.println("Du gehst nach Süden.");
        } else if (direction.contains("west") && room.west){
            curX--;
            moved = true;
            System.out.println("Du gehst nach Westen.");
        } else if (direction.contains("hoch") && room.up){
            curZ++;
            moved = true;
            System.out.println("Du gehst nach oben.");
        } else if (direction.contains("runter") && room.down){
            curZ--;
            moved = true;
            System.out.println("Du gehst nach unten.");
        } else {
            System.out.println("Hier geht es nicht weiter.");
        }
    }

    public static void showInv(){
        System.out.println("\n===================");
        int counter = 1;
        for (Item i : inv){
            System.out.printf("[%d] %s \n", counter, i.name);
            counter++;
        }
        System.out.println("===================\n");
    }

    public static void useItem(){

    }

    public static void checkItem(String name){
        for (Item i : inv){
            if (name.contains(i.name.toLowerCase())){
                System.out.println(i.desc);
            }
        }
    }

    public static void takeItem(String name) {

    }

    public static void bind (String trueName) {
        for (int i = 0; i < WarlockQuest.freeDem.size(); i++) {
            if (WarlockQuest.freeDem.get(i).trueName.equals(trueName)) {
                team.add(WarlockQuest.freeDem.get(i));
            }
        }
    }

    public static void summon(int index){
        activeDemon = team.get(index);
    }

}
