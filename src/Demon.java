public abstract class Demon {

    String name;
    String trueName;
    String textWhenSummoned;
    String textWhenBound;
    int hp;
    int hpMax;
    boolean ko = false;

    public Demon (String name, String trueName, int hp, String textWhenBound) {
        this.name = name;
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

    /*public static void bind() {
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
     */
}
