public abstract class Item {

    protected String name;
    protected String desc;
    protected boolean isConsumed = true;
    protected int puzzleID = 99;                   //ID zum Lösen des Rätsels in einem Raum, wird mit der ID des Raums abgeglichen, 99 für kein Schloss
    protected int combiID = 0;                     //ID zum Kombinieren zweier Items. Kombinieren möglich, wenn Zahl identisch und != 0

}
