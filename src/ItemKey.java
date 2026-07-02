public class ItemKey extends Item {

   protected int id;              // Schlüssel und Räume nummerieren; wenn ID übereinstimmt, passt Schlüssel und Rätsel des Raums ist gelöst

   public ItemKey (String name, String desc, int id) {
       this.name = name;
       this.desc = desc;
       this.id = id;
   }


}
