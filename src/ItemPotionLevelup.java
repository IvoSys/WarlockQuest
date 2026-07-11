public class ItemPotionLevelup extends ItemPotion {

    public ItemPotionLevelup (String name, String nameVague, String desc, int combiID) {
        this.name = name;
        this.nameVague = nameVague;
        this.desc = desc;
        this.combiID = combiID;
    }

    public boolean effect() {

        return Player.levelUp();

    }
}