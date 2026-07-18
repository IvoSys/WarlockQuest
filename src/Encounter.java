import java.util.ArrayList;

public class Encounter {

    ArrayList<Enemy> enemyTeam = new ArrayList<>();
    String teamName;
    boolean teamNamePlural = true;
    int counterKO;
    boolean beaten = false;

    String intro = "";
    String outro = "";

    Item rewardItem;

    public Encounter(String teamName, Enemy enemy1, Enemy enemy2, Enemy enemy3, String intro, String outro, Item rewardItem) {
        this.teamName = teamName;
        enemyTeam.add(enemy1);
        enemyTeam.add(enemy2);
        enemyTeam.add(enemy3);
        this.intro = intro;
        this.outro = outro;
        this.rewardItem = rewardItem;
    }


}
