public class Demon {                //Speziellere Dämonenklassen anlegen und diese abstrakt machen

    String shortName;
    String trueName;
    String textWhenBound;
    int hp;
    int hpMax;
    boolean ko = false;

    public Demon (String shortName, String trueName, int hp, String textWhenBound) {
        this.shortName = shortName;
        this.trueName = trueName;
        this.hp = hp;
        this.hpMax = hp;
        this.textWhenBound = textWhenBound;
    }

    //Setter
    public void applyDmg(int dmg){
        hp -= dmg;
        if (hp < 0)
            hp = 0;
    }

    //Angriff 1

    //Angriff 2


    public static void summon(int index){
        Player.activeDemon = Player.team.get(index);
    }

    public static void bind() {
        boolean success = false;
        System.out.print("Du machst dich bereit, einen neuen Dämon zu versklaven. \nGib seinen wahren Namen ein, und sei genau! \n> ");
        WarlockQuest.input = WarlockQuest.sc.nextLine();
        for (int i = 0; i < WorldBuilder.freeDem.size(); i++) {
            if (WorldBuilder.freeDem.get(i).trueName.equals(WarlockQuest.input)) {
                Player.team.add(WorldBuilder.freeDem.get(i));
                System.out.println(WorldBuilder.freeDem.get(i).textWhenBound);
                WorldBuilder.freeDem.remove(i);
                success = true;
                break;
            }
        }
        if (!success)
            System.out.println("Hämisches Gelächter hämmert in deinem Schädel hin und her wie der Klöppel eine Glocke. \nDieses Mal hast du keinen neuen Diener erhalten.");
    }
}
