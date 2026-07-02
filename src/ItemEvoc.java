public class ItemEvoc extends Item {

    protected String shortName;

    public ItemEvoc(String title, String desc, String shortName) {
        name = title;                   //Name der Beschwörungsformel
        this.desc = desc;               //Text mit Rätsel zum trueName des Dämonen
        this.shortName = shortName;     //Zuordnung zu beschworenem Dämonen
    }

}
