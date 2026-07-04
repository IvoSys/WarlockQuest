public class ItemEvoc extends Item {

    protected String shortName;

    public ItemEvoc(String scrollTitle, String desc, String demonShortName) {
        name = scrollTitle;                  //Name der Beschwörungsformel
        this.desc = desc;                    //Text mit Rätsel zum trueName des Dämonen
        this.shortName = demonShortName;     //Zuordnung zu beschworenem Dämonen
    }

}
