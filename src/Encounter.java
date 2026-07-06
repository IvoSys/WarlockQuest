import java.util.ArrayList;

public class Encounter {

    ArrayList<Enemy> enemyTeam = new ArrayList<>();
    String enemyTeamName;
    int counterKO;
    boolean beaten = false;

    String intro = "";
    String outro = "";

    Item rewardItem;

    public Encounter(Enemy enemy1, Enemy enemy2, Enemy enemy3) {
        enemyTeam.add(enemy1);
        enemyTeam.add(enemy2);
        enemyTeam.add(enemy3);
    }


}
