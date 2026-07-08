import java.util.Scanner;

public class Story {

    static Scanner sc = new Scanner(System.in);
    static String input = "0";
    static int refuseViolence = 0;

    static String daimonDidNotUnderstand = "DAIMON: \"Was nuschelst du da in deinen Ziegenbart?\" \n";

    public static void intro() {
        System.out.println(
                "Dieses Mal ist der König zu weit gegangen! \n" +
                        "Du, Maleficarius Liebwerk, der größte Hexenmeister, Alchemist und Dämonologe im Diesseits, sollst \"ethisch nicht mehr tragbar\" sein?! Lächerlich! \n" +
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
                "\n============================================================================================================= \n" +
                        "BEFEHLE \n" +
                        "Immer, wenn \"Zeit zu handeln!\" angezeigt wird, können folgende Befehle genutzt werden: \n\n" +
                        "Handlung\t\t\t| Eingabe\t\t\t| Effekt \n" +
                        "------------------------------------------------------------------------------------------------------------- \n" +
                        "Daimon fragen\t\t| (D)aimon \t\t\t| Bittet das Teufelchen auf deiner Schulter um Hilfe. \n" +
                        "\t\t\t\t\t|\t\t\t\t\t| \n" +
                        "Inventar zeigen\t\t| (I)tems\t\t\t| Zeigt an, welche Gegenstände du besitzt (ohne alchemistische Zutaten). \n" +
                        "Nimm Item\t\t\t| N.[Item]\t\t\t| Legt erreichbaren Gegenstand ins Inventar, z. B. \"n.Zellenschlüssel\". \n" +
                        "Untersuche Item\t\t| U.[Item]\t\t\t| Beschreibt einen Gegenstand aus dem Inventar genauer. \n" +
                        "Verwende Item \t\t| V.[Item]\t\t\t| Verwendet einen Gegenstand im Inventar, z. B. \"v.Zellenschlüssel\". \n" +
                        "\t\t\t\t\t|\t\t\t\t\t| \tVerwende Zauberschriftrollen, um einen neuen Zauber zu lernen.\n" +
                        "\t\t\t\t\t|\t\t\t\t\t| \tVerwende Beschwörungsformeln, um einen Dämon in deinen Dienst zu zwingen.\n" +
                        "\t\t\t\t\t|\t\t\t\t\t| \tVerwende sonstige Items (Schlüssel usw.), um sie im aktuellen Raum anzuwenden.\n" +
                        "Kombiniere Items\t| K.[Item].[Item]\t| Kombiniert zwei Items aus dem Inventar miteinander. \n" +
                        "\t\t\t\t\t|\t\t\t\t\t| \tZ. B. \"k.blaue Farbe.gelbe Farbe\" -> Maleficarius erhält grüne Farbe. \n" +
                        "\t\t\t\t\t|\t\t\t\t\t| \tAuf gleiche Weise kannst du aus Alchemiezutaten Tränke brauen: \n" +
                        "\t\t\t\t\t|\t\t\t\t\t| \tKombiniere rote Zutaten für Heiltränke, blaue für Manatränke.\n" +
                        "\t\t\t\t\t|\t\t\t\t\t| \n" +
                        "Gehe in Richtung\t| G.[Richtung]\t\t| Versucht, den aktuellen Ort in gewählter Richtung zu verlassen. \n" +
                        "(Raum wechseln)\t\t|\t\t\t\t\t| \tRichtungen: Nord (N), Ost (O), Süd (S), West (W), hoch (h), runter (r) \n" +
                        "\t\t\t\t\t|\t\t\t\t\t| \tz. B. \"Gehe.Norden\" oder einfach \"g.n\" \n" +
                        "\t\t\t\t\t|\t\t\t\t\t| \n" +
                        "Status anzeigen\t\t| (S)tatus\t\t\t| Zeigt den Stand von Lebens- und Magiepunkten an.\n" +
                        "Raum ansehen\t\t| (R)aum \t\t\t| Zeigt erneut Namen und Beschreibung des aktuellen Raums an. \n" +
                        "Zauberbuch\t\t\t| (Z)auber\t\t\t| Listet die von dir gelernten Zauber auf, nutze sie im Kampf.\n" +
                        "Alchemiezutaten\t\t| (A)lchemie\t\t| Zeigt deine alchemistischen Zutaten an, braue Tränke aus ihnen. \n" +
                        "Trankgurt\t\t\t| (T)ränke\t\t\t| Zeigt deine Tränke an, nutze sie AUSSERHALB des Kampfes per \"v.[Trank]\".\n" +
                        "Dämonen-Kompendium\t| Dämonen\t\t\t| Zeigt nähere Informationen zu den von dir gebundenen Dämonen an.\n" +
                        "\t\t\t\t\t|\t\t\t\t\t| \n" +
                        "Spielanleitung\t\t| (H)ilfe \t\t\t| Ruft diese Anleitung auf. \n" +
                        "Kampftutorial\t\t| Kampf \t\t\t| Ruft die Anleitung zum Kampfsystem auf. \n" +
                        //"Alchemietutorial\t| AlcheTut \t\t\t| Ruft die Anleitung zum Alchemiesystem auf. \n" +
                        //"Weitere Befehle\t\t| Befehle \t\t\t| Zeigt weitere Befehle an, die im Lauf des Spiels nützlich sein könnten. \n" +
                        "\t\t\t\t\t|\t\t\t\t\t| \n" +
                        "Mitwirkende\t\t\t| Credits \t\t\t| Zeigt die Mitwirkenden. \n" +
                        "Beenden\t\t\t\t| Ende \t\t\t\t| Beendet das Spiel. \n" +
                        "\nGroß-/Kleinschreibung wird ignoriert, außer bei Zauber- und Beschwörungsformeln. Copy-paste ist hilfreich. \n" +
                        "============================================================================================================= \n"
        );
    }

    /*
    public static void help2() {
        System.out.println(
                "\n============================================================================================================= \n" +
                        "WEITERE BEFEHLE \n" +
                        "Immer, wenn \"Zeit zu handeln!\" angezeigt wird, können folgende weitere Befehle genutzt werden: \n\n" +
                        "Handlung\t\t\t| Eingabe\t\t\t| Effekt \n" +
                        "------------------------------------------------------------------------------------------------------------- \n" +

                        "\t\t\t\t\t|\t\t\t\t\t| \n" +

                        "============================================================================================================= \n"
        );
    }

     */ //Methode für weitere Befehle

    public static void helpBattle() {
        System.out.println(
                "============================================================================================================= \n" +
                        "KAMPF \n" +
                        "Maleficarius lässt einen Dämonen für sich kämpfen und unterstützt ihn mit Hexerei. \n" +
                        "Zu Beginn des Kampfes beschwörst du einen Dämon. Wenn du am Zug bist, wird eine Liste mit Optionen angezeigt. \n" +
                        "Oben stehen die Fähigkeiten des Dämons, unter der gestrichelten Linie die Möglichkeiten von Maleficarius. \n" +
                        "Nach deinem Dämon sind die Gegner an der Reihe. Soll Maleficarius handeln, kann er dies vor dem Dämon tun, \n" +
                        "ohne dass dessen Zug verfällt. Einen Zauber zu wirken, kostet MP.\n\n" +

                        "Beispiel: \n\n" +

                        "Gegner: \n" +
                        "Soldat Max\t(53/80 HP) \tLebenslinie (2)\t\t\t (Name und Status der Gegner)\n" +
                        "Soldat Jörg\t(63/80 HP) \tÜble Saat (2)\t\t\t(\"(2)\" = verbleibende Runden des Zaubers)\n" +
                        "Soldatin Franziska\t(80/80 HP)\t \n\n\n" +

                        "100/100 HP \t\t50/50 MP \t(Dämon-Lebenspunkte und Maleficarius' Magiepunkte)\n" +
                        "[NAME DES AKTIVEN DÄMONS] \n" +
                        "[1] Angriff\t\t\t\t\t(Dämon)\n" +
                        "[2] Fähigkeit 1\t\t\t\t(Dämon)\n" +
                        "[3] Fähigkeit 2\t\t\t\t(Dämon)\n" +
                        "------------------------\n" +
                        "[4] Hexerei\t\t\t\t(Maleficarius) \n" +
                        //"[X] Trank\t\t\t\t(Maleficarius) \n" +        //Tränke nur außerhalb von Kämpfen nutzen!
                        "[5] Beschwören\t\t\t(Maleficarius) \n\n" +

                        "Du kannst auch einen neuen Dämon beschwören, anschließend sind aber zuerst die Gegner an der Reihe. \n" +
                        "Zu jedem Zeitpunkt kann nur ein Dämon im Kampf sein. \n\n" +

                        "Verschiedene Dämonen sind möglicherweise unterschiedlich stark gegen bestimmte Arten von Gegnern. \n\n" +

                        "Fallen die HP eines Dämons auf 0, kannst du einen neuen beschwören. \n" +
                        "Nach dem Kampf erhalten besiegte Dämonen 1 HP zurück. \n" +
                        "Wurden alle deine Dämonen besiegt, ist Maleficarius' Ende gekommen.\n" +
                        "============================================================================================================= \n"
        );
    }

    public static void helpAlchemy() {
        System.out.println(
                "\n============================================================================================================= \n" +
                        "ALCHEMIE \n" +
                        "Mit dem Befehl \"Kombinieren\" (k.[Item 1].[Item 2]) verwendest zu zwei Gegenstände miteinander. \n" +
                        "Dies könnte mit allen möglichen Gegenständen im Inventar funktionieren, insbesondere aber mit Alchemiezutaten. \n" +
                        "Kombiniere rote Zutaten für Heiltränke, blaue für Manatränke. \n" +
                        "Probiere alles Mögliche aus! \n\n" +
                        "Tränke kannst du AUSSERHALB VON KÄMPFEN einsetzen. \n" +
                        /*"\nALCHEMIESYSTEM VORERST ZURÜCKGESTELLT! \n" +
                        "1) Langweiliges Konzept: Jede Zutat kann aktuell nur zu genau einem Zweck verwendet werden.\n" +
                        "2) Umsetzung erfordert vergleichweise viel Arbeit.\n" +
                        "3) Heilung und Manaregeneration durch schwarze Magie wirkt interessanter, als einfach nen Trank in den Kopp zu kippen." +
                        "4) Tränke wirken neben diesem Zaubersystem redundant.\n" +
                        "-> Bereits geschriebenen Code drinlassen und bei guten Ideen wieder aktivieren.\n"+ */
                        "============================================================================================================= \n"

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
            switch (input) {
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
                    System.out.println("Eine Weile sitzt ihr schweigend da. Wie kommst du nun aus dieser Zelle heraus? \nDu siehst dich noch einmal um:");
                    break;
                default:
                    System.out.println(daimonDidNotUnderstand);
            }
        } while (!input.equals("0"));
    }

    public static void getKey000() {
        System.out.println();
        System.out.println(
                "Maleficarius liegt etwas auf der Zunge: \n" +
                        "[1]\t Sei einfach still. \n" +
                        "[2]\t Was … bist du eigentlich? \n" +
                        "[3]\t Kannst du dich durch die Gitterstäbe zwängen? \n" +
                        "[4]\t Kannst du den Wärter mit Dämonenmagie in Brand stecken? \n" +
                        "[0]\t (Lieber schweigen)"
        );
        System.out.print("> ");
        input = sc.nextLine().toLowerCase().trim();
        switch (input) {
            case "1":
                System.out.println("DAIMON: \"Na klar, Chef!\"");
                break;
            case "2":
                System.out.println("DAIMON: \"Ein Teil von jener Kraft, die stets das Böse will und stets das Gute schafft.\"");
                break;
            case "3":
                System.out.println("DAIMON: \"Ach, auf einmal hast du's eilig und willst meine Hilfe, was? \nNa gut, bin ja nicht so ein Sauertopf wie du …\" \n\nDaimon schlüpft durch die Gitterstäbe und fingert den Schlüssel aus dem Gürtelring \ndes Wärters, der dies mit verschlafenem Grunzen quittiert. Dann kehrt das Teufelchen \nzu dir zurück, dreht noch einmal um, verknotet dem Wärter die Schnürsenkel, \nschlüpft zurück in die Zelle und hält dir den Schlüssel unter die Nase. \nDAIMON: \"Da!\"\n");
                Player.inv.add(Player.room.reward);
                System.out.printf("Du erhältst den %s. \n\n", Player.room.reward.name);
                Player.room.reward = null;
                WorldBuilder.castle[0][0][0].dummyLoot = null;
                System.out.println("DAIMON: \"Und jetzt lächle und sag brav danke.\"");
                System.out.println(
                        "Wähle eine Antwort: \n" +
                                "[1]\t … \n" +
                                "[2]\t … danke. "
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
                        System.out.println(daimonDidNotUnderstand);
                }
                break;
            case "4":
                if (refuseViolence == 0) {
                    refuseViolence++;
                    System.out.println("DAIMON: \"Ich bin nicht SO ein Dämon!\"");
                } else {
                    refuseViolence++;
                    System.out.printf("DAIMON: \"Ich sag's dir jetzt zum %d. Mal: Ich bin nicht SO ein Dämon!\" \n", refuseViolence);
                }
                break;
            case "0":
                break;
            default:
                System.out.println(daimonDidNotUnderstand);
        }
    }

    //RAUM-BESCHREIBUNGEN UND DAIMON-KOMMENTARE
    //region
    static String desc000 = "Eine klamme Nische hinter Eisenstangen, gegenüber ein schnarchender Wärter mit einem Schlüssel am Gürtel. Klassiker. \nDie Tür nach Osten steht offen, aber erstmal musst du dich aus der Zelle befreien.";
    static String descSolved000 = "Eine klamme Nische hinter Eisenstangen, gegenüber ein schnarchender Wärter mit einem Schlüssel am Gürtel. Klassiker \nDie Zellentür nach Osten steht nun offen.";
    static String daimon000 = "DAIMON: \"Bei dem Schnarchen ist's nur eine Frage der Zeit, bis der Wärter die Gitterstäbe selbst durchgesägt hat …\"";
    static String daimonSolved000 = "DAIMON: \"Der Wärter ratzt einfach weiter …\"";
    static String solved000 = "Mit rostigem Knarzen schwingt die Zellentür auf, \ndu kannst nach Osten aus der Zelle heraustreten.";
    static String desc001 = "Der Wachraum liefert guten Blick auf die westliche Zelle – wenn man wach bleibt. Durch die offene Tür im Osten kriecht ein kalter Zug.";
    static String descSolved001;
    static String daimon001 = "DAIMON: \"Sieh mal zu, dass du dein Zeug wiederbekommst. Die Königsgarde hat dir alles abgenommen, bevor man dich eingebuchtet hat.\"";
    static String daimonSolved001;
    static String solved001 = "Platzhalter in Story-Klasse";
    static String desc002 = "Der Gang führt um die Ecke nach Norden, aus der Ferne hallen Geräusche.";
    static String descSolved002;
    static String daimon002 = "DAIMON: \"Platzhalter in Story-Klasse\"";
    static String daimonSolved002;
    static String solved002 = "Platzhalter in Story-Klasse";
    static String desc010 = "Platzhalter in Story-Klasse";
    static String descSolved010;
    static String daimon010 = "DAIMON: \"Platzhalter in Story-Klasse\"";
    static String daimonSolved010;
    static String solved010 = "Platzhalter in Story-Klasse";
    static String desc011 = "Platzhalter in Story-Klasse";
    static String descSolved011;
    static String daimon011 = "DAIMON: \"Platzhalter in Story-Klasse\"";
    static String daimonSolved011;
    static String solved011 = "Platzhalter in Story-Klasse";
    static String desc012 = "Platzhalter in Story-Klasse";
    static String descSolved012;
    static String daimon012 = "DAIMON: \"Platzhalter in Story-Klasse\"";
    static String daimonSolved012;
    static String solved012 = "Platzhalter in Story-Klasse";
    static String desc020 = "Platzhalter in Story-Klasse";
    static String descSolved020;
    static String daimon020 = "DAIMON: \"Platzhalter in Story-Klasse\"";
    static String daimonSolved020;
    static String solved020 = "Platzhalter in Story-Klasse";
    static String desc021 = "Platzhalter in Story-Klasse";
    static String descSolved021;
    static String daimon021 = "DAIMON: \"Platzhalter in Story-Klasse\"";
    static String daimonSolved021;
    static String solved021 = "Platzhalter in Story-Klasse";
    static String desc022 = "Platzhalter in Story-Klasse";
    static String descSolved022;
    static String daimon022 = "DAIMON: \"Platzhalter in Story-Klasse\"";
    static String daimonSolved022;
    static String solved022 = "Platzhalter in Story-Klasse";

    static String desc100 = "Platzhalter unten in Story-Klasse";
    static String descSolved100;
    static String daimon100 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved100;
    static String solved100 = "Platzhalter unten in Story-Klasse";
    static String desc101 = "Platzhalter unten in Story-Klasse";
    static String descSolved101;
    static String daimon101 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved101;
    static String solved101 = "Platzhalter unten in Story-Klasse";
    static String desc102 = "Platzhalter unten in Story-Klasse";
    static String descSolved102;
    static String daimon102 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved102;
    static String solved102 = "Platzhalter unten in Story-Klasse";
    static String desc110 = "Platzhalter unten in Story-Klasse";
    static String descSolved110;
    static String daimon110 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved110;
    static String solved110 = "Platzhalter unten in Story-Klasse";
    static String desc111 = "Platzhalter unten in Story-Klasse";
    static String descSolved111;
    static String daimon111 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved111;
    static String solved111 = "Platzhalter unten in Story-Klasse";
    static String desc112 = "Platzhalter unten in Story-Klasse";
    static String descSolved112;
    static String daimon112 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved112;
    static String solved112 = "Platzhalter unten in Story-Klasse";
    static String desc120 = "Platzhalter unten in Story-Klasse";
    static String descSolved120;
    static String daimon120 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved120;
    static String solved120 = "Platzhalter unten in Story-Klasse";
    static String desc121 = "Platzhalter unten in Story-Klasse";
    static String descSolved121;
    static String daimon121 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved121;
    static String solved121 = "Platzhalter unten in Story-Klasse";
    static String desc122 = "Platzhalter unten in Story-Klasse";
    static String descSolved122;
    static String daimon122 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved122;
    static String solved122 = "Platzhalter unten in Story-Klasse";

    static String desc200 = "Platzhalter unten in Story-Klasse";
    static String descSolved200;
    static String daimon200 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved200;
    static String solved200 = "Platzhalter unten in Story-Klasse";
    static String desc201 = "Platzhalter unten in Story-Klasse";
    static String descSolved201;
    static String daimon201 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved201;
    static String solved201 = "Platzhalter unten in Story-Klasse";
    static String desc202 = "Platzhalter unten in Story-Klasse";
    static String descSolved202;
    static String daimon202 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved202;
    static String solved202 = "Platzhalter unten in Story-Klasse";
    static String desc210 = "Platzhalter unten in Story-Klasse";
    static String descSolved210;
    static String daimon210 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved210;
    static String solved210 = "Platzhalter unten in Story-Klasse";
    static String desc211 = "Platzhalter unten in Story-Klasse";
    static String descSolved211;
    static String daimon211 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved211;
    static String solved211 = "Platzhalter unten in Story-Klasse";
    static String desc212 = "Platzhalter unten in Story-Klasse";
    static String descSolved212;
    static String daimon212 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved212;
    static String solved212 = "Platzhalter unten in Story-Klasse";
    static String desc220 = "Platzhalter unten in Story-Klasse";
    static String descSolved220;
    static String daimon220 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved220;
    static String solved220 = "Platzhalter unten in Story-Klasse";
    static String desc221 = "*** Hier werden Encounter getestet ***";
    static String descSolved221;
    static String daimon221 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved221;
    static String solved221 = "Platzhalter unten in Story-Klasse";
    static String desc222 = "*** Nebenan werden Encounter getestet ***";
    static String descSolved222;
    static String daimon222 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved222;
    static String solved222 = "Platzhalter unten in Story-Klasse";
    //endregion

    //Items mit langen Beschreibungen
    static String bookBlackArtsDesc =
            "Das Standardwerk über Hexerei, Alchemie und Dämonologie in drei Bänden, verfasst von Meister Maleficarius Liebwerk: \n" +
            "[…] Zauber werden mittels Schriftrollen erlernt und können bis zur geistigen Erschöpfung gewirkt werden. \n" +
            "[…] Tränke werden mit Verabreichung verbraucht und entfalten unvergleichliche Wirkungen, selbst bei Dämonen. \n" +
            "[…] Spricht man den wahren Namen eines Dämonen deulich aus, zwingt man ihn in seinen Dienst. Doch Obacht, er wird dies nicht schätzen!";

    //Rätsel Dämonen
    static String riddleDem00 = "Diesen okkulten Text hast du vor einer Weile schon entschlüsselt. \nDer wahre Name des Daimon lautet: \n\033[3mAgathos Daímōn Týchē, Spritus benefactum\033[0m \n\nDAIMON: \"Nooohh, Malle, ich bin doch schon bei dir!\"\n\n… ob es so eine gute Idee war, diesen Plagegeist zu beschwören?";

    static String riddleDem01 = "";
    static String trueNameDem01 = "";

    static String riddleDem02 = "";
    static String trueNameDem02 = "";

    static String riddleDem03 = "";
    static String trueNameDem03 = "";

    //Rätsel Zauber
    static String riddleBloodletting = "";
    static String formulaBloodletting = "";

    static String riddleDoom = "";
    static String formulaDoom = "";

    static String riddleIronMaiden = "";
    static String formulaIronMaiden = "";

    static String riddleLifeline = "";
    static String formulaLifeline = "";

    static String riddleSoulreaper = "";
    static String formulaSoulreaper = "Avada Kedavra";

    static String riddleViciousSeed = "";
    static String formulaViciousSeed = "";


    //Fähigkeiten von Dämonen mit flavor

    //Kills mit Flavor





}
