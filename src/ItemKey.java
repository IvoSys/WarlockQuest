public class ItemKey extends Item {

   protected int id;              // Schlüssel und Schlösser nummerieren; wenn ID übereinstimmt, passt Schlüssel

   public ItemKey (String name, String desc, int id) {
       this.name = name;
       this.desc = desc;
       this.id = id;
   }


}
