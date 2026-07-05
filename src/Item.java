public abstract class Item {

    protected String name;
    protected String namePlural;
    protected String desc;
    protected int num = 1;
    protected boolean isConsumed = true;
    protected int puzzleID = 99;                   //ID zum Lösen des Rätsels in einem Raum, wird mit der ID des Raums abgeglichen, 99 für kein Schloss
    protected int combiID = 0;                     //ID zum Kombinieren zweier Items. Zwei Items mit gleicher combiID können kombiniert werden, nur Items mit combiID = 0 können nicht kombiniert werden, daher Standardwert.

    public static boolean consumeItem(Item itemConsumed, int numConsumed){
        boolean success = false;
        boolean owned = false;
        for (Item i : Player.inv) {
            if ((itemConsumed.name.equals(i.name))){
                owned = true;
                if (i.num < numConsumed) {
                    System.out.printf("Nicht ausreichend %s im Inventar. \n", i.namePlural);
                } else {
                    i.num -= numConsumed;
                    success = true;
                    if (numConsumed == 1)
                        System.out.printf("%s verbraucht. ", i.name);
                    else
                        System.out.printf("%d %s verbraucht. ", numConsumed, i.namePlural);
                }
                if (i.num == 0) {
                        Player.inv.remove(i);
                        System.out.print("Vorrat erschöpft. \n");
                } else
                    System.out.println();
            }
        }
        if (!owned) {
            System.out.printf("%s nicht im Inventar. \n", itemConsumed.name);
        }
        if (!success) {
            System.out.printf("Kein %s wurde verbraucht. \n", itemConsumed.name);
        }
        return success;
    }

    public static void obtainItem(Item itemObtained, int numObtained){
        boolean owned = false;
        for (Item i : Player.inv) {
            if (itemObtained.name.equals(i.name)){
                i.num += numObtained;
                owned = true;
                if (numObtained == 1) {
                    System.out.printf("%s erhalten. \n", i.name);
                } else {
                    System.out.printf("%d %s erhalten. \n", numObtained, i.namePlural);
                }
                break;
            }
        }
        if (!owned){
            for (int i = 0; i < numObtained; i++) {
                Player.inv.add(itemObtained);
            }
            if (numObtained == 1)
                System.out.printf("%s erhalten \n", itemObtained.name);
            else
                System.out.printf("%d %s erhalten \n", numObtained, itemObtained.namePlural);
        }
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
