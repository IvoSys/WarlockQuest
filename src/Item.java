public abstract class Item {

    protected String name;
    protected String desc;
    protected boolean isConsumed = true;
    protected int puzzleID = 99;                   //ID zum Lösen des Rätsels in einem Raum, wird mit der ID des Raums abgeglichen, 99 für kein Schloss

}
