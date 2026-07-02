import java.util.ArrayList;

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

    public static void move(String input){
        if (input.contains("nord") && room.north){
            curY++;
            moved = true;
            System.out.println("Du gehst nach Norden.");
        } else if (input.contains("ost") && room.east){
            curX++;
            moved = true;
            System.out.println("Du gehst nach Osten.");
        } else if (input.contains("süd") && room.south){
            curY--;
            moved = true;
            System.out.println("Du gehst nach Süden.");
        } else if (input.contains("west") && room.west){
            curX--;
            moved = true;
            System.out.println("Du gehst nach Westen.");
        } else if (input.contains("hoch") && room.up){
            curZ++;
            moved = true;
            System.out.println("Du gehst nach oben.");
        } else if (input.contains("runter") && room.down){
            curZ--;
            moved = true;
            System.out.println("Du gehst nach unten.");
        } else {
            System.out.println("Hier geht es nicht weiter.");
        }
    }

    public static void daimon(){
        System.out.println(room.daimon);            //Daimon-Kommentar für diesen Raum
        if (room.puzzleID == 0) {                   //In bestimmten Räumen führt Daimon speziele Methoden aus
            if(!room.solved) {
                Story.getKey000();
                room.solved = true;
            }
        }
    }

    public static void showInv(){
        System.out.println("\n===================");
        for (Item i : inv){
            System.out.printf("· %s \n", i.name);
        }
        System.out.println("===================\n");
    }

    public static void useKey(String input){
        boolean found = false;
        for (Item i : inv){
            if (input.contains(i.name.toLowerCase())){      // Wenn Eingabe den Namen eines Key-Items im Inventar enthält und
                if(i.puzzleID == room.puzzleID){            // wenn die puzzleID dieses Key-Items zu der des aktuellen Raums passt,
                    WarlockQuest.solveRoom();               // Raum gelöst!
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

    public static void checkItem(String input){
        boolean found = false;
        for (Item i : inv){
            if (input.contains(i.name.toLowerCase())){
                System.out.println(i.desc);
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("So etwas habe ich nicht.");
    }

    public static void loot(String input) {
        if (room.loot != null){
            if(input.contains(room.loot.name.toLowerCase())){
                inv.add(room.loot);
                System.out.printf("Du nimmst an dich: %s \n", room.loot.name);
                room.loot = null;
            } else {
                System.out.println("So etwas sehe ich hier nicht.");
            }
        } else {
            System.out.println("Hier ist nichts zu holen.");
        }
    }

    public static void bind() {
        boolean success = false;
        System.out.print("Du machst dich bereit, einen neuen Dämon zu versklaven. \nGib seinen wahren Namen ein, und sei genau! \n> ");
        WarlockQuest.input = WarlockQuest.sc.nextLine();
        for (int i = 0; i < WarlockQuest.freeDem.size(); i++) {
            if (WarlockQuest.freeDem.get(i).trueName.equals(WarlockQuest.input)) {
                team.add(WarlockQuest.freeDem.get(i));
                System.out.println(WarlockQuest.freeDem.get(i).bound);
                WarlockQuest.freeDem.remove(i);
                success = true;
                break;
            }
        }
        if (!success)
            System.out.println("Hämisches Gelächter hallt in deinem Schädel hin und her. \nDieses Mal hast du keinen neuen Diener erhalten.");
    }

    public static void summon(int index){
        activeDemon = team.get(index);
    }

}
