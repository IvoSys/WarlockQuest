public class PotionMana extends Potion {

    public PotionMana(String name, String namePlural, String desc, int str, int combiID) {
        this.name = name;
        this.namePlural = namePlural;
        this.desc = desc;
        this.str = str;
        this.combiID = combiID;
    }

    public void drink() {
        Player.mp += str;
        if (Player.mp > Player.mpMax) {
            Player.mp = Player.mpMax;
        }
    }



}
