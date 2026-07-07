public class PotionHealth extends Potion {


    public PotionHealth(String name, String namePlural, String desc, int str, int combiID) {
        this.name = name;
        this.namePlural = namePlural;
        this.desc = desc;
        this.str = str;
        this.combiID = combiID;
    }

    public void drink() {
       Player.activeDemon.hp += str;
       if (Player.activeDemon.hp > Player.activeDemon.hpMax) {
           Player.activeDemon.hp = Player.activeDemon.hpMax;
        }
    }







}
