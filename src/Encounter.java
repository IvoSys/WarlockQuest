import java.util.ArrayList;

public class Encounter {

    ArrayList<Enemy> enemyTeam = new ArrayList<>();
    String teamName;
    boolean nameIsPlural;
    int counterKO;
    boolean beaten = false;

    String intro = "";
    String outro = "";

    Item rewardItem1;
    Item rewardItem2;

    public Encounter(String teamName, boolean nameIsPlural, Enemy enemy1, Enemy enemy2, Enemy enemy3, String intro, String outro, Item rewardItem1,  Item rewardItem2) {
        this.teamName = teamName;
        this.nameIsPlural = nameIsPlural;
        enemyTeam.add(enemy1);
        enemyTeam.add(enemy2);
        enemyTeam.add(enemy3);
        this.intro = intro;
        this.outro = outro;
        this.rewardItem1 = rewardItem1;
        this.rewardItem2 = rewardItem2;
    }


}
