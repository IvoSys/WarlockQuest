import java.util.ArrayList;

public class Player {

    static ArrayList<Item> inv = new ArrayList<>();
    static ArrayList<Demon> team = new ArrayList<>();
    static Demon activeDemon;

    public static void showInv(){

    }

    public static void useItem(){

    }

    public static void checkItem(){

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
