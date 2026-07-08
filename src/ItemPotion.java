public abstract class ItemPotion extends Item{

    protected int str;

    public ItemPotion() {}

    public static void drink(String trank) {
        for (Item i : Player.inv) {
            if (i instanceof ItemPotion && i.name.equals(trank)) {
                if (((ItemPotion) i).effect())
                    Item.consumeItem(i);
                break;
            }
        }
    }

    public abstract boolean effect();

}
