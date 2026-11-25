public class RoomObject {

    String name = "";                       // Objekt kann über Namen mit b. oder n. angesteuert werden.
    String name2 = "";                      // Wenn Loot mit gleichem Namen enthalten,
    String name3 = "";                          // sollten Namen 1+2 mit name und nameVague des Loots übereinstimmen,
    String name4 = "";                          // name 3+4 leer, sonst könnte über name 3 oder 4 tryLoot ausgegeben werden, bevor Loot erhalten wurde
    String descFull;                        // Wenn loot vorhanden, Beschreibung mit Hinweis auf loot.
    String descEmpty;                       // Wenn kein loot (mehr) vorhanden, Beschreibung ohne Hinweis auf loot.
    String tryLoot;                         // Textausgabe, wenn Spieler versucht, Raumobjekt zu nehmen.
    Item loot;
    // sogar noch verwendbar machen mit Verwenden-Methode und Switch-Case?


    // Konstruktoren mit 1/2/3/4 ansprechbaren Namen
    public RoomObject(String name, String descFull, String descEmpty, String tryLoot, Item loot) {
        this.name = name;
        this.descFull = descFull;
        this.descEmpty = descEmpty;
        this.tryLoot = tryLoot;
        this.loot = loot;
    }

    public RoomObject(String name, String name2, String descFull, String descEmpty, String tryLoot, Item loot) {
        this.name = name;
        this.name2 = name2;
        this.descFull = descFull;
        this.descEmpty = descEmpty;
        this.tryLoot = tryLoot;
        this.loot = loot;
    }

    public RoomObject(String name, String name2, String name3, String descFull, String descEmpty, String tryLoot, Item loot) {
        this.name = name;
        this.name2 = name2;
        this.name3 = name3;
        this.descFull = descFull;
        this.descEmpty = descEmpty;
        this.tryLoot = tryLoot;
        this.loot = loot;
    }

    public RoomObject(String name, String name2, String name3, String name4, String descFull, String descEmpty, String tryLoot, Item loot) {
        this.name = name;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
        this.descFull = descFull;
        this.descEmpty = descEmpty;
        this.tryLoot = tryLoot;
        this.loot = loot;
    }



}
