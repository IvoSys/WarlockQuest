public class ItemScroll extends Item {

    protected int str;
    protected int num;

    public ItemScroll(String name, String desc, int str) {
        this.name = name;
        this.desc = desc;
        this.str = str;
        num = 1;
    }

    public int cast(){
        return str;
    }
}
