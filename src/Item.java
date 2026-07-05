public abstract class Item {

    protected String name;
    protected String desc;
    protected int num = 1;
    protected boolean isConsumed = true;
    protected int puzzleID = 99;                   //ID zum Lösen des Rätsels in einem Raum, wird mit der ID des Raums abgeglichen, 99 für kein Schloss
    protected int combiID = 0;                     //ID zum Kombinieren zweier Items. Kombinieren möglich, wenn Zahl identisch und != 0

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
