import java.util.Scanner;

public class Story {

    static Scanner sc = new Scanner(System.in);
    static String input = "0";

    public static void intro() {
        System.out.println(
            "Dieses Mal ist der König zu weit gegangen! \n" +
            "Du, Maleficarius Liebwerk, der größte Hexenmeister und Dämonologe im Diesseits, sollst \"ethisch nicht mehr tragbar\" sein?! Lächerlich! \n" +
            "Wo stünde dieses kümmerliche Reich schon ohne deine Forschung? \"Aber Meister Liebwerk, Ihr lÄsTeRt DeR ScHöPfUnG GoTtEs!\". Pahh!\n\n" +
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
            "Items (I): \t\t\tZeigt das Inventar an. \n" +
            "Nimm [Item]: \t\tLegt einen erreichbaren Gegenstand ins Inventar, z. B. \"Nimm Zellenschlüssel\". \n" +
            "Nutze [Item]: \t\tVerwendet den angegebenen Gegenstand. \n" +
            "Prüfe [Item]: \t\tRuft eine genauere Beschreibung zum angegebenen Gegenstand ab. \n" +
            "Binden (B): \t\tZwingt einen neuen Dämon in deinen Dienst. \n" +
            "Daimon (D): \t\tBittet das Teufelchen auf deiner Schulter um Hilfe. \n" +
            "Hilfe (H): \t\t\tRuft diese Anleitung auf. \n" +
            "Ende (E): \t\t\tBeendet das Spiel. \n" +
            "\n(Groß-/Kleinschreibung wird ignoriert, außer beim Binden. Copy-paste ist möglich.) \n" +
            "===============================\n"
        );
    }

    public static void daimonIntro() {
        System.out.println(
            "\"Malefiz, alter Halunke, da hast du dir aber was eingebrockt! \n" +
            "Kein Wunder, dass ein Griesgram wie du irgendwann im Kerker endet.\" \n\n" +
            "Daimon, dein persönliches Teufelchen, erscheint auf deiner Schulter \n" +
            "und spielt schelmisch mit seiner Schwanzspitze. \n"
        );
        do {
            System.out.println(
                    "Wähle eine Antwort: \n" +
                    "[1]\t Ich kann diesen Spitznamen nicht leiden. \n" +
                    "[2]\t Diese Zelle ist furchtbar. \n" +
                    "[3]\t Ich kann dich nicht ausstehen. \n" +
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
                    System.out.println("Daimon: \"Du kannst ja nicht mal dich selbst ausstehen.\"\n");
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

    public static void getKey000(){
        System.out.println();
        System.out.println("DAIMON: \"Ach, auf einmal hast du's eilig und willst du meine Hilfe, was? \nNa gut, bin ja nicht so ein Sauertopf wie du …\" \nDaimon schlüpft durch die Gitterstäbe, fingert den Schlüssel aus dem Gürtelring \ndes Wärters, der dies mit verschlafenem Grunzen quittiert, und kehrt zu dir zurück.\n");
        Player.inv.add(WarlockQuest.key000);
        System.out.printf("Du erhältst einen %s. \n\n", WarlockQuest.key000.name);
        System.out.println("DAIMON: \"Und jetzt lächle und sag brav Danke.\"");
        System.out.println(
                "Wähle eine Antwort: \n" +
                        "[1]\t … \n" +
                        "[2]\t … Danke. "
        );
        System.out.print("> ");
        input = sc.nextLine().toLowerCase().trim();
        switch (input) {
            case "1":
                System.out.println("DAIMON: \"Meh, was hab ich erwartet.\"\n");
                break;
            case "2":
                System.out.println("DAIMON: \"Na, das hat doch gar nicht weh getan!\"\n");
                break;
            default:
                System.out.println("DAIMON: \"Was nuschelst du schon wieder?!\"\n");
        }
    }

    //RAUM-BESCHREIBUNGEN UND DAIMON-KOMMENTARE
    //region
    static String desc000 = "Eine klamme Nische hinter Eisenstangen, gegenüber ein schnarchender Wärter mit einem Schlüssel am Gürtel. Klassiker. \nDie Tür nach Osten steht offen, aber erstmal musst du aus der Zelle rauskommen.";
    static String daimon000 = "DAIMON: \"Mit dem Schnarchen nur eine Frage der Zeit, bis der Wärter die Gitterstäbe selbst durchgesägt hat …\"";
    static String desc001 = "Der Wachraum liefert guten Blick auf die westliche Zelle – wenn man wach bleibt. Durch die offene Tür im Osten kriecht ein kalter Zug.";
    static String daimon001 = "DAIMON: \"Sieh mal zu, dass du dein Zeug wiederbekommst. Bevor sie dich eingebuchtet hat, hat dir die Königsgarde alles abgenommen.\"";
    static String desc002 = "Der Gang führt um die Ecke nach Norden, aus der Ferne hallen Geräusche.";
    static String daimon002 = "DAIMON: \"\"";
    static String desc010 = "";
    static String daimon010 = "DAIMON: \"\"";
    static String desc011 = "";
    static String daimon011 = "DAIMON: \"\"";
    static String desc012 = "";
    static String daimon012 = "DAIMON: \"\"";
    static String desc020 = "";
    static String daimon020 = "DAIMON: \"\"";
    static String desc021 = "";
    static String daimon021 = "DAIMON: \"\"";
    static String desc022 = "";
    static String daimon022 = "DAIMON: \"\"";

    static String desc100 = "";
    static String daimon100 = "DAIMON: \"\"";
    static String desc101 = "";
    static String daimon101 = "DAIMON: \"\"";
    static String desc102 = "";
    static String daimon102 = "DAIMON: \"\"";
    static String desc110 = "";
    static String daimon110 = "DAIMON: \"\"";
    static String desc111 = "";
    static String daimon111 = "DAIMON: \"\"";
    static String desc112 = "";
    static String daimon112 = "DAIMON: \"\"";
    static String desc120 = "";
    static String daimon120 = "DAIMON: \"\"";
    static String desc121 = "";
    static String daimon121 = "DAIMON: \"\"";
    static String desc122 = "";
    static String daimon122 = "DAIMON: \"\"";

    static String desc200 = "";
    static String daimon200 = "DAIMON: \"\"";
    static String desc201 = "";
    static String daimon201 = "DAIMON: \"\"";
    static String desc202 = "";
    static String daimon202 = "DAIMON: \"\"";
    static String desc210 = "";
    static String daimon210 = "DAIMON: \"\"";
    static String desc211 = "";
    static String daimon211 = "DAIMON: \"\"";
    static String desc212 = "";
    static String daimon212 = "DAIMON: \"\"";
    static String desc220 = "";
    static String daimon220 = "DAIMON: \"\"";
    static String desc221 = "";
    static String daimon221 = "DAIMON: \"\"";
    static String desc222 = "";
    static String daimon222 = "DAIMON: \"\"";
    //endregion



}
