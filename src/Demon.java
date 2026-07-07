public abstract class Demon {

    String name;
    String trueName;
    String textWhenSummoned;
    String textWhenBound;

    int hp;
    int hpMax;
    int power = 10;

    boolean ko = false;

    public Demon () {}

    //Setter
    public void applyDmg(int dmg){
        hp -= dmg;
        if (hp < 0)
            hp = 0;
        if (hp == 0)
            ko = true;
    }

    public void applyHeal(int heal){
        if (!ko) {
            hp += heal;
            if (hp > hpMax)
                hp = hpMax;
        }
    }


    public abstract int attack();

    public abstract int ability1();

    public abstract int ability2();

    public static void summon(int index){
        Player.activeDemon = Player.team.get(index);
        System.out.println(Player.activeDemon.textWhenSummoned);
    }

    public static void bind() {
        boolean success = false;
        System.out.print("Du machst dich bereit, einen neuen Dämon zu versklaven. \nGib seinen wahren Namen ein, und sei genau! \n> ");
        WarlockQuest.input = WarlockQuest.sc.nextLine();
        for (int i = 0; i < WorldBuilder.freeDem.size(); i++) {
            if (WorldBuilder.freeDem.get(i).trueName.equals(WarlockQuest.input)) {
                if (!Player.team.contains(WorldBuilder.freeDem.get(i))) {
                Player.team.add(WorldBuilder.freeDem.get(i));
                System.out.println(WorldBuilder.freeDem.get(i).textWhenBound);
                //WorldBuilder.freeDem.remove(i);
                } else {
                    System.out.println("Diesen Dämon hast du bereits gebunden.");
                }
                success = true;
                break;
            }
        }
        if (!success)
            System.out.println("Hämisches Gelächter hämmert in deinem Schädel hin und her wie der Klöppel eine Glocke. \nDieses Mal hast du keinen neuen Diener erhalten.");

    }

}
