public class RoomObject {

    String name;
    String name2;
    String name3;
    String name4;
    String descFull;
    String descEmpty;
    Item loot;

    public RoomObject(String name, String descFull, String descEmpty, Item loot) {
        this.name = name;
        this.descFull = descFull;
        this.descEmpty = descEmpty;
        this.loot = loot;
    }

    public RoomObject(String name, String name2, String descFull, String descEmpty, Item loot) {
        this.name = name;
        this.name2 = name2;
        this.descFull = descFull;
        this.descEmpty = descEmpty;
        this.loot = loot;
    }

    public RoomObject(String name, String name2, String name3, String descFull, String descEmpty, Item loot) {
        this.name = name;
        this.name2 = name2;
        this.name3 = name3;
        this.descFull = descFull;
        this.descEmpty = descEmpty;
        this.loot = loot;
    }

    public RoomObject(String name, String name2, String name3, String name4, String descFull, String descEmpty, Item loot) {
        this.name = name;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
        this.descFull = descFull;
        this.descEmpty = descEmpty;
        this.loot = loot;
    }



}
