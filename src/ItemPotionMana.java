public class ItemPotionMana extends ItemPotion{

    public ItemPotionMana (String name, String nameVague, String desc, int str, int combiID) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.str = str;
        this.combiID = combiID;
    }

    public boolean effect() {
        boolean sucess = false;

        if (Player.mp == Player.mpMax) {
            System.out.println("Maleficarius ist nicht erschöpft.");
        } else {
            Player.mp += str;
            sucess = true;
            if (Player.mp > Player.mpMax) {
                Player.mp = Player.mpMax;
            }
        }
        return sucess;
    }
}
