import java.util.Scanner;

public class Story {

    static Scanner sc = new Scanner(System.in);
    static String input = "0";

    public static void intro() {
        System.out.println(
            "Dieses Mal ist der König zu weit gegangen! \n" +
            "Du, Maleficarius Liebwerk, der größte Hexenmeister und Dämonologe im Diesseits, sollst \"ethisch nicht mehr tragbar\" sein?! Lächerlich! \n" +
            "Wo stünde dieses kümmerliche Reich schon ohne deine Forschung? Die Annehmlichkeiten genießen diese Heuchler gerne, \n" +
            "doch den Preis zahlen, das wollen sie nicht! \"Aber Meister Liebwerk, Ihr lÄsTeRt DeR ScHöPfUnG GoTtEs!\". Pahh!\n\n" +
            "Und dann haben sie dich in den Kerker geworfen. Bauerntrampel ohne Ambition oder Vorstellungskraft! \n" +
            "Es wird Zeit, den König über kleiner Flamme zu rösten! \n"
        );
    }

    public static void needHelp() {
        System.out.println("Dein Kopf brodelt vor Wut. Du solltest erst einmal wieder zu klaren Gedanken kommen. Wie ging das Ganze hier noch gleich?");
        System.out.println("Anleitung anzeigen? (j/n)");
        System.out.print("> ");
    }

    public static void help() {
        System.out.println(
            "\n=============================== \n" +
            "BEFEHLE \n" +
            "Immer, wenn \"Zeit zu handeln!\" angezeigt wird, können folgende Befehle genutzt werden: \n\n" +
            "Gehe [Richtung]: \tVersucht, den aktuellen Ort in gewählter Richtung zu verlassen. \n" +
                    "\t\t\t\t\tRichtungen: Nord, Ost, Süd, West, hoch, runter; z. B. \"Gehe Nord\" \n" +
            "Items: \t\t\t\tZeigt das Inventar an. \n" +
            "Nimm [Item]: \t\tLegt einen erreichbaren Gegenstand ins Inventar, z. B. \"Nimm Zellenschlüssel\". \n" +
            "Nutze [Item]: \t\tVerwendet den angegebenen Gegenstand. \n" +
            "Prüfe [Item]: \t\tRuft eine genauere Beschreibung zum angegebenen Gegenstand ab. \n" +
            "Binden: \t\t\tZwingt einen neuen Dämon in deinen Dienst. \n" +
            "Beschwören: \t\tRuft einen an dich gebundenen Dämon herbei. \n" +
            "Daimon: \t\t\tBittet das Teufelchen auf deiner Schulter um Hilfe. \n" +
            "Hilfe: \t\t\t\tRuft diese Anleitung auf. \n" +
            "Ende: \t\t\t\tBeendet das Spiel. \n" +
            "\n(Groß-/Kleinschreibung wird ignoriert.) \n" +
            "===============================\n"
        );
    }

    public static void daimon01() {
        System.out.println(
            "\"Malefiz, alter Halunke, da hast du dir aber was eingebrockt! \n" +
            "Kein Wunder, dass ein Griesgram wie du irgendwann im Kerker endet.\" \n\n" +
            "Daimon, dein persönliches Teufelchen, erscheint auf deiner Schulter. \n" +
            "[Daimon macht etwas Charakterisierendes.] \n"
        );
        do {
            System.out.println(
                    "Wähle eine Antwort: \n" +
                    "[1]\t Ich kann diesen Spitznamen nicht leiden. \n" +
                    "[2]\t Ich kann diese Zelle nicht leiden. \n" +
                    "[3]\t Ich kann dich nicht leiden. \n" +
                    "[4]\t Ich hasse den König. \n" +
                    "[0]\t (Schweigen)"
            );
            System.out.print("> ");
            input = sc.nextLine().toLowerCase().trim();
            switch (input){
                case "1":
                    System.out.println("Daimon: \"Und ich kann meinen vollen Namen nicht leiden. Hat dich aber auch nicht interessiert. \nIm Gegenteil, der Herr Liebwerk muss natürlich ein Riesending darum aufziehen, mit Beschwörungsformel und allem. \nTja.\n");
                    break;
                case "2":
                    System.out.println("Daimon: \"Dabei siehst du so aus, als hättest du lange bei deiner Mutter im Keller gewohnt.\"\n");
                    break;
                case "3":
                    System.out.println("Daimon: \"Du kannst ja nicht mal dich selbst leiden.\"\n");
                    break;
                case "4":
                    System.out.println("Daimon: \"Er mag dich anscheinend auch nicht. Die Zelle ist bestimmt ein Diss speziell gegen dich – schau, die \nGitterstäbe sind so weit auseinander, dass du nicht durchpasst, ich aber schon. Bestimmt mag er mich lieber als dich.\"\n");
                    break;
                case "0":
                    System.out.println("Eine Weile sitzt ihr schweigend da. Wie kommst du nun aus dieser Zelle heraus?");
                    break;
                default:
                    System.out.println("Daimon: \"Was murmelst du da?\"\n");
            }
        } while (!input.equals("0"));
    }
}
