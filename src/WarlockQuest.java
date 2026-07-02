import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WarlockQuest {

    static Room[][][] castle = new Room[3][3][3];
    static int curX = 0;
    static int curY = 0;
    static int curZ = 0;

    static boolean running = true;

    static Scanner sc = new Scanner(System.in);
    static String input;

    //Schloss bauen
    public static void buildCastle(){

        //UG, Reihe unten
        castle[0][0][0] = new Room("Kerker", "", false, false, false, false, false, false);
        castle[0][0][1] = new Room("", "", false, false, false, false, false, false);
        castle[0][0][2] = new Room("", "", false, false, false, false, false, false);

        //UG, Reihe Mitte
        castle[0][1][0] = new Room("", "", false, false, false, false, false, false);
        castle[0][1][1] = new Room("", "", false, false, false, false, false, false);
        castle[0][1][2] = new Room("", "", false, false, false, false, false, false);

        //UG, Reihe oben
        castle[0][2][0] = new Room("", "", false, false, false, false, false, false);
        castle[0][2][1] = new Room("", "", false, false, false, false, false, false);
        castle[0][2][2] = new Room("", "", false, false, false, false, false, false);

        //EG, Reihe unten
        castle[1][0][0] = new Room("", "", false, false, false, false, false, false);
        castle[1][0][1] = new Room("", "", false, false, false, false, false, false);
        castle[1][0][2] = new Room("", "", false, false, false, false, false, false);

        //EG, Reihe Mitte
        castle[1][1][0] = new Room("", "", false, false, false, false, false, false);
        castle[1][1][1] = new Room("", "", false, false, false, false, false, false);
        castle[1][1][2] = new Room("", "", false, false, false, false, false, false);

        //EG, Reihe oben
        castle[1][2][0] = new Room("", "", false, false, false, false, false, false);
        castle[1][2][1] = new Room("", "", false, false, false, false, false, false);
        castle[1][2][2] = new Room("", "", false, false, false, false, false, false);

        //OG, Reihe unten
        castle[2][0][0] = new Room("", "", false, false, false, false, false, false);
        castle[2][0][1] = new Room("", "", false, false, false, false, false, false);
        castle[2][0][2] = new Room("", "", false, false, false, false, false, false);

        //OG, Reihe Mitte
        castle[2][1][0] = new Room("", "", false, false, false, false, false, false);
        castle[2][1][1] = new Room("", "", false, false, false, false, false, false);
        castle[2][1][2] = new Room("", "", false, false, false, false, false, false);

        //OG, Reihe oben
        castle[2][2][0] = new Room("", "", false, false, false, false, false, false);
        castle[2][2][1] = new Room("", "", false, false, false, false, false, false);
        castle[2][2][2] = new Room("", "", false, false, false, false, false, false);



    }

    //Dämonen vorbereiten
    Demon dem01 = new Demon();

    static Demon[] allDem = {dem01};
    static List<Demon> freeDem = new ArrayList<>(Arrays.asList(allDem));


    //Gegner bauen

    //Items bauen

    static void main(String[] args){

        ASCII.title();
        Story.intro();
        Story.needHelp();
        input = sc.nextLine().toLowerCase().trim();
        if (input.equals("j")){
            Story.help();
            Control.enterToContinue();
        }
        System.out.println();
        Story.daimon01();
        System.out.println("DEBUG: Ende erreicht");

    }


}
