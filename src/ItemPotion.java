public class ItemPotion extends Item {

    protected int str;
    protected int num;

    public ItemPotion(String name, String desc, int str) {
        this.name = name;
        this.desc = desc;
        this.str = str;
        num = 1;
    }

    public int heal(){
        return str;
    }

}
