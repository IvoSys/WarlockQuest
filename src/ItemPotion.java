public class ItemPotion extends Item {

    protected int str;

    public ItemPotion(String name, String namePlural, String desc, int str, int combiID) {
        this.name = name;
        this.namePlural = namePlural;
        this.desc = desc;
        this.str = str;
        this.combiID = combiID;
    }

    public int heal(){
        return str;
    }

}
