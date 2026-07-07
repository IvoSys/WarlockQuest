public abstract class Potion {

    //entspricht ITEM
    protected String name;
    protected String namePlural;
    protected String desc;
    protected int num = 1;
    protected boolean isConsumed = true;
    protected int puzzleID = 99;                   //ID zum Lösen des Rätsels in einem Raum, wird mit der ID des Raums abgeglichen, 99 für kein Schloss
    protected int combiID = 0;                     //ID zum Kombinieren zweier Items. Zwei Items mit gleicher combiID können kombiniert werden, nur Items mit combiID = 0 können nicht kombiniert werden, daher Standardwert.

    protected int str;

    public Potion() {}

    public abstract void drink();

    public static boolean removePotion(Potion potRemoved) {
        boolean success = false;

        if (Player.potions.contains(potRemoved)) {
            int i = Player.potions.indexOf(potRemoved);
            Player.potions.get(i).num--;
            System.out.printf("%s verbraucht", Player.potions.get(i).name);
            success = true;
            if  (Player.potions.get(i).num <= 0) {
                Player.potions.remove(i);
                System.out.println(", Vorrat erschöpft.");
            } else {
                System.out.println(".");
            }
        } else {
            System.out.println("Item nicht im Inventar.");
        }
        return success;
    }

    public static void obtainPotion(Potion potObtained) {
        if (Player.potions.contains(potObtained)) {
            int i = Player.potions.indexOf(potObtained);
            Player.potions.get(i).num++;
        } else {
            Player.potions.add(potObtained);
        }
        System.out.printf("%s erhalten \n", potObtained.name);
    }


}
