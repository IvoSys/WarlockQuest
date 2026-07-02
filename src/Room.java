public class Room {

    protected String name;
    protected String desc;
    protected String daimon;
    protected boolean solved = false;
    protected String solvedText;
    protected boolean north, east, south, west, up, down;   //In welche Richtungen der Raum verlassen werden kann
    protected Item loot;                                    //Offen einsammelbarer Gegenstand
    protected int puzzleID;                                 //ID zum Lösen des Rätsels eines Raums, muss mit Key-Item oder in Daimon-Methode übereinstimmen, von Raumkoordinate abgeleitet
    protected Item reward;                                  //Nach Lösung des Rätsels erhältlicher Gegenstand

    public Room(String name, String desc, String daimon, boolean north, boolean east, boolean south, boolean west, boolean up, boolean down, int puzzleID) {
        this.name = name;
        this.desc = desc;
        this.daimon = daimon;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.up = up;
        this.down = down;
        this.puzzleID = puzzleID;
    }
}
