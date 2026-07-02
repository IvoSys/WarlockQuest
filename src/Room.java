public class Room {

    protected String name;
    protected String desc;
    protected boolean north, east, south, west, up, down;   //In welche Richtungen der Raum verlassen werden kann
    protected int id;                                       //Schlüssel und Räume nummerieren; wenn ID übereinstimmt, passt Schlüssel und Rätsel des Raums ist gelöst
    protected Item loot;                                    //offen einsammelbarer Gegenstand
    protected Item reward;                                  //Nach Lösugn des Rätsels erhältlicher Gegenstand

    public Room(String name, String desc, boolean north, boolean east, boolean south, boolean west, boolean up, boolean down, int id) {
        this.name = name;
        this.desc = desc;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.up = up;
        this.down = down;
        this.id = id;
    }
}
