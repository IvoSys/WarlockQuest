import java.util.ArrayList;

public class Player {

    static ArrayList<Item> inv = new ArrayList<>();
    static ArrayList<Demon> team = new ArrayList<>();
    static Demon activeDemon;

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

    public static void use(int index){
        inv.get(index).effect();
        if (inv.get(index).isConsumed){
            inv.get(index).num--;
            if (inv.get(index).num == 0)
                inv.remove(index);
        }

    }

}
