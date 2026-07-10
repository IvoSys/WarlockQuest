public class ItemPotionLevelup extends ItemPotion {

    public ItemPotionLevelup (String name, String desc, int combiID) {
        this.name = name;
        this.desc = desc;
        this.combiID = combiID;
    }

    public boolean effect() {

        return Player.levelUp();

    }
}