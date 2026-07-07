public abstract class Item {

    protected String name;
    protected String namePlural;
    protected String desc;
    protected int num = 1;
    protected boolean isConsumed = true;
    protected int puzzleID = 0;                    //ID zum Lösen des Rätsels in einem Raum, wird mit der ID des Raums abgeglichen, 0 für neutral, room000 stattdessen -1
    protected int combiID = 0;                     //ID zum Kombinieren zweier Items. Zwei Items mit gleicher combiID können kombiniert werden, Items mit combiID = 0 können nicht kombiniert werden, daher Standardwert.


    public static boolean consumeItem(Item itemConsumed) {
        boolean success = false;

        if (Player.inv.contains(itemConsumed)) {
            int i = Player.inv.indexOf(itemConsumed);
            Player.inv.get(i).num--;
            System.out.printf("%s verbraucht", Player.inv.get(i).name);
            success = true;
            if  (Player.inv.get(i).num <= 0) {
                Player.inv.remove(i);
                System.out.println(", Vorrat erschöpft.");
            } else {
                System.out.println(".");
            }
        } else {
            System.out.println("Item nicht im Inventar.");
        }
        return success;
    }

    public static boolean consumeItemMult(Item itemConsumed, int numConsumed) {
        boolean success = false;

        if (Player.inv.contains(itemConsumed)) {
            int i = Player.inv.indexOf(itemConsumed);
            if (Player.inv.get(i).num >= numConsumed) {
                Player.inv.get(i).num -= numConsumed;
                success = true;
                System.out.printf("%d %s verbraucht", numConsumed, Player.inv.get(i).namePlural);
            } else {
                System.out.printf("Du hast nicht genug %s. \n", itemConsumed.namePlural);
            }
            if  (Player.inv.get(i).num <= 0) {
                Player.inv.remove(i);
                System.out.println(", Vorrat erschöpft.");
            } else {
                System.out.println(".");
            }
        } else {
            System.out.println("Item nicht im Inventar.");
        }
        return success;
    }

    public static void obtainItem(Item itemObtained) {
        if (Player.inv.contains(itemObtained)) {
            int i = Player.inv.indexOf(itemObtained);
            Player.inv.get(i).num++;
        } else {
            Player.inv.add(itemObtained);
        }
        System.out.printf("%s erhalten \n", itemObtained.name);
    }

    public static void obtainItemMult(Item itemObtained, int numObtained) {
        if (Player.inv.contains(itemObtained)) {
            int i = Player.inv.indexOf(itemObtained);
            Player.inv.get(i).num += numObtained;
        } else {
            Player.inv.add(itemObtained);
            Player.inv.getLast().num = numObtained;
        }
        System.out.printf("%d %s erhalten \n", numObtained, itemObtained.namePlural);
    }

}



/*
Je zwei unterschiedliche Items mit gleicher combiID =! 0 ergeben

404 reserviert für Kombinieren-Methode


ID      ZUTATEN                             PRODUKT         ID
1       (rote Zutaten)                      Heiltrank       10
2       (blaue Zutaten)                     Manatrank       20
3
4
5
6
7
10
11
12
13
14
15
16
17
18
19
20

 */
