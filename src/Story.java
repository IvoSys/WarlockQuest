import java.util.Scanner;

public class Story {

    static Scanner sc = new Scanner(System.in);
    static String input = "0";

    static int refuseViolence = 0;
    static String daimonDidNotUnderstand = "DAIMON: \"Was nuschelst du da in deinen Ziegenbart?\" \n";



    public static void inputLine() {
        System.out.print("> ");
        Story.input = Story.sc.nextLine().toLowerCase().trim();
    }

    public static void enterToContinue() {
        System.out.println("(Weiter mit Eingabetaste)");
        System.out.print("> ");
        try {
            System.in.read();
        }
        catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    //Fähigkeiten von Dämonen mit flavor

    //Kills mit Flavor
}

class Daimon extends Story {

    public static void speak() {
        System.out.println(Player.room.daimon);                           // Daimon-Kommentar für diesen Raum

        if ((Player.room.ID == -1) && (Player.room.reward != null)) {     // Methoden für bestimmte Räume
            getKey000();
        }
        if ((Player.room.ID == 12) && (!Player.team.contains(BuildItems.dem01))) {
            brainstorm012();
        }
        if ((Player.room.ID == 10)) {
            forAlfred010();
        }
    }

    public static void intro() {
        System.out.println(
                """
                        DAIMON: "Malefiz, alter Halunke, da hast du dir aber was eingebrockt!
                        Kein Wunder, dass ein Griesgram wie du irgendwann im Kerker endet."
                        
                        Daimon, dein persönliches Teufelchen, erscheint in einer aufbrausenden Flamme,
                        hockt sich betont lässig auf deine Schulter und spielt schelmisch mit seiner Schwanzspitze.
                        """);
        do {
            System.out.println(
                    """
                            Wähle eine Antwort:
                            [1]\t Ich kann diesen Spitznamen nicht leiden.
                            [2]\t Diese Zelle ist furchtbar.
                            [3]\t Ich kann dich nicht ausstehen.
                            [4]\t Ich hasse den König.
                            [0]\t (Schweigen)"""
            );
            System.out.print("> ");
            input = sc.nextLine().toLowerCase().trim();
            switch (input) {
                case "1":
                    System.out.println("DAIMON: \"Und ich kann meinen richtigen Namen nicht leiden. Hat dich aber auch nicht interessiert. \nIm Gegenteil, der Herr Liebwerk muss natürlich ein Riesending darum aufziehen, mit Beschwörungsformel und allem. \nTja.\"\n");
                    break;
                case "2":
                    System.out.println("DAIMON: \"Dabei siehst du so aus, als hättest du lange bei deiner Mutter im Keller gewohnt.\"\n");
                    break;
                case "3":
                    System.out.println("DAIMON: \"Du kannst ja nicht mal dich selbst ausstehen.\"\n");
                    break;
                case "4":
                    System.out.println("DAIMON: \"Ach, er dich auch. Aber mich mag er bestimmt – schau, die Gitterstäbe \nstehen gerade so weit auseinander, dass du nicht durchpasst, ich aber schon.\"\n");
                    break;
                case "0":
                    System.out.println("Eine Weile sitzt ihr schweigend da.");
                    enterToContinue();
                    System.out.println("Irgendwann wird Daimon auf deine Umhängetasche aufmerksam.");
                    break;
                default:
                    System.out.println(daimonDidNotUnderstand);
            }
        } while (!input.equals("0"));
    }

    public static void intro2() {
        System.out.println(
                """
                        DAIMON: "Ach schau, du hast ja noch deinen ganzen Kram dabei. Zauberbuch, Alchemiebeutel …
                        Nimmt man dem bösen Hexenmeister sowas nicht weg, bevor man ihn in den Kerker wirft?
                        """
        );
        do {
            System.out.println(
                    """
                            Wähle eine Antwort:
                            [1]\t Sie haben die Seiten aus dem Zauberbuch gerissen.
                            [2]\t Sie haben meine Trankzutaten gestohlen.
                            [3]\t Sie haben den Codex infernalis verunstaltet!
                            [4]\t Ich muss ganz von vorne anfangen.
                            [0]\t (Schweigen)"""
            );
            System.out.print("> ");
            input = sc.nextLine().toLowerCase().trim();
            switch (input) {
                case "1":
                    System.out.println("In gespielter Bestürzung wirft sich Daimon einen Handrücken vor die Stirn.");
                    System.out.println("DAIMON: \"Oh nein! Dabei dienen die Runen in den Formeln doch als Fokus, um die Magick zu kanalisieren. \nSelbst wenn du die Arcana aus dem Gedächtnis wüsstest, ließen sie sich ohne das Zauberbuch nicht adäquat katalysieren!\"");
                    enterToContinue();
                    System.out.println("DAIMON: \"… so klingst du, Malibub. So klingst du.\"\n");
                    break;
                case "2":
                    System.out.println("DAIMON: \"Ugh, wer klaut denn Molchaugen und Spinnenbeine?! Wer, Mal, wer?");
                    System.out.println("Also abgesehen von Leuten, die sich Getränke daraus mixen. Wie du.\"\n");
                    break;
                case "3":
                    System.out.println("DAIMON: \"Dieses satanische Beschwörungsbuch? \nHaben sie den Dämonen etwa falsche Schnurrbärte aufgemalt?\"\n");
                    break;
                case "4":
                    System.out.println("DAIMON: \"Nicht gaaaanz von vorne, du hast doch mich, Maleficus! \nLass uns ein paar Flüche lernen, Tinkturen brauen und Dämonen versklaven! \nUnd dann stürzen wir den König und unterwerfen das Reich!\"");
                    enterToContinue();
                    System.out.println("DAIMON: \"Also … gleich sobald du hier raus bist.\"\n");
                    break;
                case "0":
                    System.out.println("Wieder sitzt ihr schweigend da. Wie kommst du nun aus dieser Zelle heraus? \n");
                    break;
                default:
                    System.out.println(daimonDidNotUnderstand);
            }
        } while (!input.equals("0"));

    }

    public static void getKey000() {
        System.out.println();
        System.out.println(
                """
                        Maleficarius liegt etwas auf der Zunge:
                        [1]\t Sei einfach still.
                        [2]\t Was … bist du eigentlich?
                        [3]\t Kannst du dich durch die Gitterstäbe zwängen?
                        [4]\t Kannst du den Wärter mit Dämonenmagie in Brand stecken?
                        [0]\t (Lieber schweigen)"""
        );
        System.out.print("> ");
        input = sc.nextLine().toLowerCase().trim();
        switch (input) {
            case "1": System.out.println("DAIMON: \"Na klar, Chef!\""); break;
            case "2": System.out.println("DAIMON: \"Ein Teil von jener Kraft, die stets das Böse will und stets das Gute schafft.\""); break;
            case "3":
                System.out.println("DAIMON: \"Ach, auf einmal hast du's eilig und willst meine Hilfe, was? \nNa gut, bin ja nicht so ein Sauertopf wie du …\" \n\nDaimon schlüpft durch die Gitterstäbe und fingert den Schlüssel aus dem Gürtelring \ndes Wärters, der dies mit verschlafenem Grunzen quittiert. Dann kehrt das Teufelchen \nzu dir zurück, dreht noch einmal um, verknotet dem Wärter die Schnürsenkel, \nhuscht zurück in die Zelle und hält dir den Zellenschlüssel unter die Nase. \nDAIMON: \"Da, nimm!\"");
                Player.room.loot.add(Player.room.reward);
                Player.room.reward = null;
                break;
            case "4": refuseViolence(); break;
            case "0": break;
            default: System.out.println(daimonDidNotUnderstand);
        }
    }

    public static void refuseViolence() {
        if (refuseViolence == 0) {
            refuseViolence++;
            System.out.println("DAIMON: \"Ich bin nicht SO ein Dämon!\"");
        } else {
            refuseViolence++;
            System.out.printf("DAIMON: \"Ich sag's dir jetzt zum %d. Mal: Ich bin nicht SO ein Dämon!\" \n", refuseViolence);
        }
    }

    public static void solved000Comment() {
        System.out.println("DAIMON: \"Und jetzt lächle und sag brav danke.\"");
        System.out.println(
                "Wähle eine Antwort: \n" +
                        "[1]\t … \n" +
                        "[2]\t … danke. "
        );
        System.out.print("> ");
        input = sc.nextLine().toLowerCase().trim();
        switch (input) {
            case "1": System.out.println("DAIMON: \"Meh, was hab ich erwartet.\""); break;
            case "2": System.out.println("DAIMON: \"Na, das hat doch gar nicht weh getan!\""); break;
            default:
                System.out.println(daimonDidNotUnderstand);
        }
    }

    public static void brainstorm012() {
        System.out.println();
        System.out.println(
                """
                        Maleficarius liegt etwas auf der Zunge:
                        [1]\t Sei einfach still.
                        [2]\t Was … bist du eigentlich?
                        [3]\t Kannst du die Wachen mit deinen Dämonenklauen ist Stücke reißen?
                        [0]\t (Lieber schweigen)""");
        System.out.print("> ");
        input = sc.nextLine().toLowerCase().trim();
        switch (input) {
            case "1":
                System.out.println("DAIMON: \"Na klar, Chef!\"");
                break;
            case "2":
                System.out.println("DAIMON: \"Ach Malefex, frag ich dich etwa, warum sie dich in den Kerker geworfen haben?\"");
                break;
            case "3":
                refuseViolence();
                break;
            case "0":
                break;
            default:
                System.out.println(daimonDidNotUnderstand);
        }
    }

    public static void forAlfred010() {
        System.out.println("""
            Wähle eine Antwort:
            [1] … Alfred haben wir vorhin vermutlich selbst umgebracht.
            [2] Der Mann hatte recht, Essiggurken und Käsebrot gehören zusammen.
            [0] (Schweigen)
            """);
        inputLine();
        switch (input) {
            case "1": System.out.println("DAIMON: \"Er hätte es sicher so gewollt, dass wir den Aufseher auch umlegen!\""); break;
            case "2":
                System.out.println("DAIMON: \"Ja ja, wie Leberwurst und Butter.\"\n");
                System.out.println("""
                Wähle eine Antwort:
                [1] Du bist wahrhaftig eine Kreatur aus der Hölle.
                [2] Genau.""");
                inputLine();
                switch (input) {
                    case "1": System.out.println("DAIMON: \"Und doch ist Leberwurst mit Butter eine irdische Erfindung. Die Teufel sind schon hier.\""); break;
                    case "2": System.out.println("DAIMON: \"Das war Sarkasmus, du Monstrum.\""); break;
                    default: System.out.println(daimonDidNotUnderstand); break;
                }
                break;
            case "0": System.out.println("DAIMON: \"Aber nett von dir, dass du ihm das Käsebrot WIRKLICH gemacht hast, obwohl das Ganze eh nur ein Trick war.\""); break;
            default: System.out.println(daimonDidNotUnderstand); break;
        }
    }

}

class Descriptions extends Story {

    //RAUM-BESCHREIBUNGEN UND DAIMON-KOMMENTARE
    //region
    //Zelle
    static String desc000 = "Eine klamme Nische hinter Eisenstangen, gegenüber ein schnarchender Wärter mit einem Schlüssel am Gürtel. Klassiker. \n";
    static String descDoors000 = "Die Tür nach Osten steht offen, aber erstmal musst du dich aus der Zelle befreien.";
    static String descDoorsB000 = "Die Zellentür nach Osten steht nun offen.";
    static String descSolved000;
    static String daimon000 = "DAIMON: \"Bei dem Schnarchen ist's nur eine Frage der Zeit, bis der Wärter die Gitterstäbe selbst durchgesägt hat …\"";
    static String daimonSolved000 = "DAIMON: \"Der Wärter ratzt einfach weiter …\"";
    static String unlocked000 = "Mit rostigem Knarzen schwingt die Zellentür auf, \ndu kannst nach Osten aus der Zelle heraustreten.\n";
    //Kerker
    static String desc001 = "Der Wachraum liefert guten Blick auf die westliche Zelle – wenn man wach bleibt. \nDurch die offene Tür im Osten kriecht ein kalter Zug. \nDer Wächter schläft immer noch. Auf dem Tisch steht eine Stundenkerze und eine Schale Radieschen.";
    static String descDoors001;
    static String descSolved001;
    static String daimon001 = "DAIMON: \"Sieh mal zu, dass du dein Zeug wiederbekommst. Die Königsgarde hat dir alles abgenommen, bevor man dich eingebuchtet hat.\"";
    static String daimonSolved001;
    static String solved001 = "";
    //Kellergang
    static String desc002 = "Aus Westen kommend führt der Gang um die Ecke nach Norden, aus der Ferne hallen Geräusche. \nAus der Ostwand sind einige Steine ausgebrochen, die auf dem Boden verstreut liegen, \ndoch hinter der Mauer tritt nur wurzeldurchzogenes Erdreich zutage.";
    static String descDoors002;
    static String descB002 = "Aus Westen kommend führt der Gang um die Ecke nach Norden. \nAus der Ostwand sind einige Steine ausgebrochen, die auf dem Boden verstreut liegen, \ndoch hinter der Mauer tritt nur wurzeldurchzogenes Erdreich zutage.";
    static String descSolved002;
    static String daimon002 = "DAIMON: \"Ein langer Gang ohne was drin? Faule Architekten.\"";
    static String daimonSolved002;
    static String solved002 = "";
    //Offiziersquartier
    static String desc010 = "Auf dem schweren Holztisch in der Mitte des Raumes liegt eine Schriftrolle ausgebreitet, \nbeschwert mit einem Glas mit einer konservierten dunklen Wurzel aus deinem eigenen Vorrat. \nAn der Südwand sind Waffen- und Rüstungsständer mit Knüppeln, Schwertern, Schilden und Rüstungen aufgereiht. \nIm Kamin prasselt ein Feuer.";
    static String descDoors010;
    static String descSolved010;
    static String daimon010 = "Daimon spuckt auf Aufseher Ottos Leiche.\nDAIMON: \"Das war für Alfred!\"";
    static String daimonSolved010;
    static String solved010;
    static String enc010Intro = "Verduzt kauend starrt der Aufseher dich an. Nach angestrengtem Schlucken prustet er empört: \"Du bist nicht Alfred!\", \nein Stück Essiggurke wird aus seinem Mund katapultiert. \nDie beiden vor dem Feuer zusammengerollten Hunde erwachen. Der Aufseher zieht sein Schwert.";
    static String enc010Outro = "Du holst dir den Kerkerschlüssel von Ottos Kette. Dein Blick fällt auf deine Besitztümer auf dem Schreibtisch, \nals laute Rufe durch das Kerkergewölbe hallen. Wer im Schlafsaal noch geschlafen hat, ist jetzt wohl wach.";
    //Vorratskammer
    static String desc011 = """
            Eine Vielzahl von Gerüchen umgibt dich: Von der Decke hängen verschiedene Würste und Schinken,
            ein angeschnittener Laib Käse liegt auf einem Tisch, daneben einige in Leinen geschlagene Brote.
            An der Wand sind Fässer mit Bier und Wein aufgestapelt, ein weiteres Fass ist mit Essiggurken gefüllt. """;
    static String descDoors011 = "Die Tür im Osten führt zurück in den Vorraum.";
    static String descSolved011;
    static String daimon011 = "DAIMON: \"Eine Vorratskammer! Nimm alles mit, was nicht niet- und nagelfest ist!\"";
    static String daimonSolved011;
    static String solved011 = "Platzhalter in Story-Klasse";
    //Vorraum (UG)
    static String desc012 = """
            Der Vorraum des Untergeschosses. Im Westen sitzt eine mit "Vorratskammer" beschriftete Tür in der Wand.
            Hinter einer Gittertür – gesichert mit schwerem Vorhängeschloss – führt eine breite Steintreppe hinauf ins Erdgeschoss.
            Im Süden liegt der Kerker. Im Norden geht es zur Kantine, aus der das Grölen einiger Wachen hallt.
            """;
    static String descDoors012;
    static String descB012 = """
            Der Vorraum des Untergeschosses. Im Westen sitzt eine mit "Vorratskammer" beschriftete Tür in der Wand.
            Hinter einer Gittertür – gesichert mit schwerem Vorhängeschloss – führt eine breite Steintreppe hinauf ins Erdgeschoss.
            Im Süden liegt der Kerker. Im Norden geht es zur Wachkantine.
            """;
    static String descSolved012;
    static String daimon012 = "DAIMON: \"Schau dir meine Arme an – dünn wie Zahnstocher! Ganz zu schweigen von den welken Lauchstangen, die dir aus den Schultern wuchern. \nWenn du den Wachen ohne Argumentationsverstärker vor den Knüppel kommst, machen sie Hexerkompott aus dir.\"";
    static String daimonB012 = "DAIMON: \"Jetzt hast du endlich SO einen Dämon. Also Mal, was hält dich noch – grau ist alle Theorie!\"";
    static String daimonSolved012;
    static String solved012 = "Platzhalter in Story-Klasse";
    static String enc012Intro = """
    "Stehen bleiben, Hexer!"
    Dame Beatrix steht vor dir, die sagenumwobene Schwertmeisterin. Ihr schneidender Anblick soll weinende Kinder verstummen lassen.
    An ihrer Seite stehen eine Heilerin und der (jetzt nicht mehr schlafende) Wächter aus dem Kerker, mit einer auffälligen Schramme am Kinn.
    Beatrix zieht ihren Zweihänder: "Dein nächster Zellenaufenthalt wird weit weniger angenehm."
    """;
    static String enc012Outro = "";
    //Waschraum
    static String desc020 = """
            Am Westende ist ein Kessel aufgestellt, aus dem, wenn angeheizt, warmes Wasser geschöpft werden kann. Daneben ein großer Kübel mit kaltem Wasser.
            Einige Badezuber stehen im Raum verteilt. Hier und da liegen Haarbüschel am Boden.
            Im Norden steht eine schwere, fleckige Holzbank – anscheinend ein Operationstisch. Östlich geht es zum Schlafsaal.
            Im Süden eine Tür, darüber steht deutlich geschrieben: "Offiziersstube, Waffenausgabe". Sie ist abgeschlossen, aber ein Murmeln ist dahinter zu hören.
            """;
    static String descDoors020;
    static String descSolved020;
    static String daimon020 = "DAIMON: \"Da drüben, die Offiziersstube, da murmelt wer. Vielleicht sollten wir einfach mal reinrufen, ob wer mit uns spricht?\"";
    static String daimonSolved020 = "DAIMON: \"Der Horror-Chef hat die Tür zur Offiziersstube offen gelassen.\"";
    static String solved020 = "Platzhalter in Story-Klasse";
    static String descPostBattle020 = """
            Am Westende ist ein Kessel aufgestellt, aus dem, wenn angeheizt, warmes Wasser geschöpft werden kann. Daneben ein großer Kübel mit kaltem Wasser.
            Einige Badezuber stehen im Raum verteilt. Hier und da liegen Haarbüschel am Boden.
            Im Norden steht eine schwere, fleckige Holzbank – anscheinend ein Operationstisch. Östlich geht es zum Schlafsaal.
            Im Süden eine Tür, darüber steht deutlich geschrieben: "Offiziersstube, Waffenausgabe".
            """;
    //Schlafsaal
    static String desc021 = "Es ist spät am Abend und einige Betten sind von Wachen aus der Frühschicht belegt. Du schleichst auf Zehenspitzen. \nAn der Nordwand steht eine große Tafel mit der Überschrift \"Mitarbeitende des Monats\". \nNach Westen geht es in die Badestube, nach Osten in die Wachkantine.";
    static String descDoors021;
    static String descSolved021;
    static String daimon021 = "DAIMON: \"Schau mal, da hat einer Mitternachtskraut auf dem Nachttisch liegen. \nDer hätte dem Gefängniswärter besser was davon abgegeben. Also besser für den Wärter, nicht für dich.\"";
    static String daimonSolved021;
    static String solved021;
    static String enc021Intro;
    static String enc021Outro;

    //Wachkantine
    static String desc022 = "Speise- und Aufenthaltsraum der Wachbelegschaft. Die langen Tische sind spartanisch gedeckt, \nnur auf einem lässt ein Blümchen in einem halbvollen Wassergals den Kopf hängen. \nÜber dem glimmenden Kaminfeuer hängt ein großer Topf. \nDie Tür im Süden führt in den Vorraum, nach Westen geht es in den Schlafsaal.";
    static String descDoors022;
    static String descSolved022;
    static String daimon022 = "DAIMON: \"Du solltest zusehen, deinen Dämon zu heilen, bevor es weitergeht. \nAlso nicht mich – den mit der großen Axt.\"";
    static String daimonSolved022;
    static String solved022;
    static String enc022Intro = "An einem der Tische sitzen drei Wachen mit Bierhumpen.\nEiner hält ein Stück auseinandergerolltes Pergament in der Hand und liest wild gestikulierend daraus vor, \ndie anderen kichern rotnasig. Dramatisch reckt der Vortragende seine Faust gen Zimmerdecke, \nwährend er eine ausgedachte Zauberformel rezitiert und seine Stimme immer höher schraubt, \nals würde sie sich vor Aufregung überschlagen. \nDabei wirkt er irgendwie wie … wie du! \nDie anderen Wachen brechen in haltloses Gelächter aus. \nAngesäuert stößt du die Tür auf, drei Augenpaare wenden sich überrascht zu dir um. ";
    static String enc022Outro = "Röchelnd gehen die Wachen zu Boden. Im nächsten Leben wird ihnen das eine Lehre sein. \nDu sammelst die Schriftrolle auf.";

    static String desc100 = "Platzhalter unten in Story-Klasse";
    static String descDoors100;
    static String descSolved100;
    static String daimon100 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved100;
    static String solved100 = "Platzhalter unten in Story-Klasse";
    static String desc101 = "Platzhalter unten in Story-Klasse";
    static String descDoors101;
    static String descSolved101;
    static String daimon101 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved101;
    static String solved101 = "Platzhalter unten in Story-Klasse";
    static String desc102 = "Platzhalter unten in Story-Klasse";
    static String descDoors102;
    static String descSolved102;
    static String daimon102 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved102;
    static String solved102 = "Platzhalter unten in Story-Klasse";
    static String desc110 = "Platzhalter unten in Story-Klasse";
    static String descDoors110;
    static String descSolved110;
    static String daimon110 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved110;
    static String solved110 = "Platzhalter unten in Story-Klasse";
    static String desc111 = "Platzhalter unten in Story-Klasse";
    static String descDoors111;
    static String descSolved111;
    static String daimon111 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved111;
    static String solved111 = "Platzhalter unten in Story-Klasse";
    static String desc112 = "Platzhalter unten in Story-Klasse";
    static String descDoors112;
    static String descSolved112;
    static String daimon112 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved112;
    static String solved112 = "Platzhalter unten in Story-Klasse";
    static String desc120 = "Platzhalter unten in Story-Klasse";
    static String descDoors120;
    static String descSolved120;
    static String daimon120 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved120;
    static String solved120 = "Platzhalter unten in Story-Klasse";
    static String desc121 = "Platzhalter unten in Story-Klasse";
    static String descDoors121;
    static String descSolved121;
    static String daimon121 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved121;
    static String solved121 = "Platzhalter unten in Story-Klasse";
    static String desc122 = "Platzhalter unten in Story-Klasse";
    static String descDoors122;
    static String descSolved122;
    static String daimon122 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved122;
    static String solved122 = "Platzhalter unten in Story-Klasse";

    static String desc200 = "Platzhalter unten in Story-Klasse";
    static String descDoors200;
    static String descSolved200;
    static String daimon200 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved200;
    static String solved200 = "Platzhalter unten in Story-Klasse";
    static String desc201 = "Platzhalter unten in Story-Klasse";
    static String descDoors201;
    static String descSolved201;
    static String daimon201 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved201;
    static String solved201 = "Platzhalter unten in Story-Klasse";
    static String desc202 = "Platzhalter unten in Story-Klasse";
    static String descDoors202;
    static String descSolved202;
    static String daimon202 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved202;
    static String solved202 = "Platzhalter unten in Story-Klasse";
    static String desc210 = "Platzhalter unten in Story-Klasse";
    static String descDoors210;
    static String descSolved210;
    static String daimon210 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved210;
    static String solved210 = "Platzhalter unten in Story-Klasse";
    static String desc211 = "Platzhalter unten in Story-Klasse";
    static String descDoors211;
    static String descSolved211;
    static String daimon211 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved211;
    static String solved211 = "Platzhalter unten in Story-Klasse";
    static String desc212 = "Platzhalter unten in Story-Klasse";
    static String descDoors212;
    static String descSolved212;
    static String daimon212 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved212;
    static String solved212 = "Platzhalter unten in Story-Klasse";
    static String desc220 = "Platzhalter unten in Story-Klasse";
    static String descDoors220;
    static String descSolved220;
    static String daimon220 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved220;
    static String solved220 = "Platzhalter unten in Story-Klasse";
    static String desc221 = "*** Hier werden Encounter getestet ***";
    static String descDoors221;
    static String descSolved221;
    static String daimon221 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved221;
    static String solved221 = "Platzhalter unten in Story-Klasse";
    static String desc222 = "*** Im Raum westlich werden Encounter getestet ***";
    static String descDoors222;
    static String descSolved222;
    static String daimon222 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved222;
    static String solved222 = "Platzhalter unten in Story-Klasse";
    //endregion

    //Items mit langen Beschreibungen
    static String bookBlackArtsDesc =
            """
                    Das Standardwerk über Hexerei, Alchemie und Dämonologie, verfasst von Meister Maleficarius Liebwerk:
                    […] Zauber werden mittels Schriftrollen im Zauberbuch festgehalten und können dann bis zur geistigen Erschöpfung gewirkt werden.
                    […] Tränke werden aus gleichfarbigen Ingredienzen gebraut und entfalten unvergleichliche Wirkungen, selbst bei Dämonen.
                    […] Spricht man den wahren Namen eines Dämonen deutlich aus, zwingt man ihn in seinen Dienst. Doch Obacht, er wird dies nicht schätzen!""";

    static String employeeOfTheMonth =
            """
                    Du trittst näher an die Tafel heran und studierst die Aufschrift.
                    
                    === MITARBEITENDE DES MONDES – A.D. 1475 ===
                    Eismond:\t
                    Thaumond:\t
                    Lenzmond:\t
                    Keimmond:\t
                    Wonnemond:\t
                    Brachmond:\t
                    Heumond:\t
                    Erntemond:\t
                    Herbstmond:\t
                    Weinmond:\t
                    Nebelmond:\t
                    Julmond:\t
                    ============================================
                    
                    DAIMON: "Die müssen sich wohl ganz schön ins Zeug gelegt haben, damit der Laden hier läuft."
                    """;

    //Rätsel Dämonen
    static String riddleDem00 = """
            Diese okkulte Formel hast du vor einer Weile schon entschlüsselt
            und ihre Kraft entladen, als du den Dämon an dich gebunden hast.
            
            Der wahre Name des vertrauten Geistes lautet:
            \033[3mGenius Daimonion, Spritus familiaris\033[0m
            
            DAIMON: "Nooohh, Malle, ich bin doch schon bei dir!"
            
            … ob es so eine gute Idee war, diesen Plagegeist zu beschwören?
            """;

    static String riddleDem01 = """
            Du blickst auf die Schriftrolle, da steht:
            
            Du hörst es hallen, das Gebrüll aus den Gängen.
            Wenn er dich in die Irre führt, findest du dann noch zurück?
            Schau hinab und suche den Pflasterstein, der vorher kam,
            ein Schritt nach dem anderen:
            ;ompzsitpd
            """;
    static String trueNameDem01 = "Minotauros";

    static String riddleDem02 = """
            Du blickst auf die Schriftrolle, das wellige Papier
            ist an den Rändern angesengt, und immer wieder wird
            der Text von verbrannte Stellen unterbrochen:
            
            Gebor⬤n aus Feuer und Wüstenwind
            Deinem Willen ⬤eindlich gesinnt
            Ein Geist de⬤ Flammen, lod⬤rnder Zorn
            Sprich m⬤inen Namen, oder du bis⬤ verlor'n
            """;
    static String trueNameDem02 = "Efreet";

    static String riddleDem03 = """
            Du blickst auf die Schriftrolle, die Formel enthält nichts als einen tiefschwarzen Tintenklecks:
            
            ⠀⠀⠀⠀⠀⠀⠀         ⠀⠀⠀\u001B[30m\u001B[40m⢀⣀⣤⣤⣤⣤⣴⣤⣤⣄⡀\u001B[0m⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀\u001B[30m\u001B[40m⣀⣴⣾⠿⠛⠋⠉⠁⠀⠀⠀⠈⠙⠻⢷⣦⡀\u001B[0m⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀\u001B[30m\u001B[40m⣤⣾⡿⠋⠁⠀⣠⣶⣿⡿⢿⣷⣦⡀⠀⠀⠀⠙⠿⣦⣀\u001B[0m⠀⠀⠀⠀
                    ⠀⠀\u001B[30m\u001B[40m⢀⣴⣿⡿⠋⠀⠀⢀⣼⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⢻⣿⣷⣶⣄\u001B[0m⠀
                    ⠀\u001B[30m\u001B[40m⣴⣿⣿⠋⠀⠀⠀⠀⠸⣿⣿Abaddon⣿⣿⡆⠀⠀⠐⡄⡌⢻⣿⣿⡷\u001B[0m
                    \u001B[30m\u001B[40m⢸⣿⣿⠃⢂⡋⠄⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⢦⣷⣿⠿⠛⠁\u001B[0m
                    ⠀\u001B[30m\u001B[40m⠙⠿⢾⣤⡈⠙⠂⢤⢀⠀⠙⠿⢿⣿⣿⡿⠟⠁⠀⣀⣀⣤⣶⠟⠋⠁\u001B[0m⠀⠀⠀
                    ⠀⠀⠀⠀\u001B[30m\u001B[40m⠈⠙⠿⣾⣠⣆⣅⣀⣠⣄⣤⣴⣶⣾⣽⢿⠿⠟⠋\u001B[0m⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀\u001B[30m\u001B[40m⠉⠙⠛⠛⠙⠋⠉⠉\u001B[0m⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            
            Du fühlst dich beobachtet.
            """;

    static String trueNameDem03 = "Abaddon";
}

class Dialogue extends Story {

    public static void speak() {
        if (!Player.room.hasDialogue)
            System.out.println("Niemand da.");
        else {
            switch (Player.room.ID) {
                case -1: dialogue000(); break;
                case 1: dialogue001(); break;
                case 20: dialogue020(); break;

                default:
                    System.out.println("DEBUG: Dialog nicht gefunden.");
                    break;
            }
        }

    }

    public static void dialogue000() {
        System.out.println("DAIMON: \"Den solltest du lieber schlafen lassen.\"");
    }

    public static void dialogue001() {
        System.out.println("DAIMON: \"Den solltest du lieber schlafen lassen.\"");
    }

    public static void dialogue020() {
        if (!Player.inv.contains(BuildItems.cheeseBread) && !Player.inv.contains(BuildItems.sandwich)) {
            System.out.println("STIMME HINTER DER TÜR: \"Alfred? ALFRED! Schleichst du da draußen herum?");
            System.out.println(
                    """
                            Wähle eine Antwort:
                            [1]\t Nein.
                            [2]\t … ja?
                            [0]\t (Lieber schweigen)"""
            );
            inputLine();
            switch (input) {
                case "1": System.out.println("STIMME HINTER DER TÜR: \"Alfred, du Witzbold. Bring mir lieber mein abendliches Käsebrot. \nUnd zwar WIE ÜBRLICH, und ein wenig zackig!\""); break;
                case "2": System.out.println("STIMME HINTER DER TÜR: \"Was Alfred, bist du dir nicht mal deiner eigenen Identität sicher? \nSei nicht so ein Waschlappen und bring mir mein abendliches Käsebrot. Und zwar WIE ÜBRLICH, und ein wenig zackig!\""); break;
                case "0": System.out.println("STIMME HINTER DER TÜR: \"Alfred? Aaaaalfred!\""); System.out.println("Du entfernst dich."); break;
                default: System.out.println("STIMME HINTER DER TÜR: \"Alfred, mit dem Herumgedruckse wird das noch länger nichts mit der Beförderung. \nSei nicht so ein Waschlappen und bring mir mein abendliches Käsebrot. Und zwar WIE ÜBRLICH, und ein wenig zackig!\""); break;
            }
        }
        else if (Player.inv.contains(BuildItems.cheeseBread)) {
            System.out.println("STIMME HINTER DER TÜR: \"Alfred? Hast du endlich mein Käsebrot? Hast du's auch richtig gemacht?");
            System.out.println(
                    """
                            Wähle eine Antwort:
                            [1]\t Nein.
                            [2]\t Einmal Käsebrot. Mit Käse. Und Brot. Was könnte man da falsch machen?
                            [0]\t (Lieber schweigen)"""
            );
            inputLine();
            switch (input) {
                case "1": System.out.println("STIMME HINTER DER TÜR: \"Dann mach mal hinne, Alfred!"); break;
                case "2": System.out.println("STIMME HINTER DER TÜR: \"Man könnte zum Beispiel die Essiggurke vergessen, Alfred. \nMan könnte sie sogar STÄNDIG vergessen, wie du, Alfred. \nDeshalb schiebst du nach sechs Monaten auch immer noch Wachdienst. \nUnd selbst den kriegst du nur, weil die Gefängniszellen von alleine zu bleiben, Alfred. \nUnd jetzt leg verdammt noch mal Essiggurke aufs Käsebrot!\""); System.out.println("\nDAIMON: \"Uff, der ist ja fast so ein schlechter Chef wie du, Mal.\""); break;
                case "0": System.out.println("STIMME HINTER DER TÜR: \"Alfred? Aaaaalfred!\""); System.out.println("Du entfernst dich."); break;
                default: System.out.println("STIMME HINTER DER TÜR: \"Alfred, mit dem Herumgedruckse wird das noch länger nichts mit der Beförderung. \nSei nicht so ein Waschlappen und bring mir mein mittägliches Käsebrot. Und zwar WIE ÜBRLICH, und ein wenig zackig!\""); break;
            }
        }

        else if (Player.inv.contains(BuildItems.sandwich)) {
            System.out.println("STIMME HINTER DER TÜR: \"Alfred? Hast du endlich mein Käsebrot? Hast du's auch richtig gemacht?");
            System.out.println(
                    """
                            Wähle eine Antwort:
                            [1]\t Nein.
                            [2]\t Einmal Käsebrot mit Essiggurke für den Chef.
                            [0]\t (Lieber schweigen)"""
            );
            inputLine();
            switch (input) {
                case "1":
                    System.out.println("STIMME HINTER DER TÜR: \"Dann mach mal hinne, Alfred!");
                    break;
                case "2":
                    BuildCastle.castle[0][2][0].south = true;
                    BuildCastle.castle[0][2][0].daimon = Descriptions.daimonSolved020;
                    Item.consume(BuildItems.sandwich);
                    System.out.println("STIMME HINTER DER TÜR: \"Wurde auch Zeit.\"");
                    System.out.println("Mit einem Klacken wird die Tür entriegelt, öffnet sich einen Spalt, eine Hand kommt hervor, greift sich das Käsebrot und zieht sich hinter die Tür zurück. \nDie Tür schließt sich wieder, doch das Schloss klickt nicht erneut.");
                    break;
                case "0":
                    System.out.println("STIMME HINTER DER TÜR: \"Alfred? Aaaaalfred!\"");
                    System.out.println("Du entfernst dich.");
                    break;
                default:
                    System.out.println("STIMME HINTER DER TÜR: \"Alfred, mit dem Herumgedruckse wird das noch länger nichts mit der Beförderung. \nSei nicht so ein Waschlappen und bring mir mein mittägliches Käsebrot. Und zwar WIE ÜBRLICH, und ein wenig zackig!\"");
                    break;
            }
        }

    }

}

class Intro extends Story {

    public static void intro() {
        System.out.println(
                """
                        Dieses Mal ist der König zu weit gegangen!
                        Du, Maleficarius Liebwerk, der größte Hexenmeister, Alchemist und Dämonologe im Diesseits, sollst "ethisch nicht mehr tragbar" sein?! Lächerlich!
                        Wo stünde dieses kümmerliche Reich schon ohne deine Forschung? "Aber Meister Liebwerk, Ihr lÄsTeRt DeR ScHöPfUnG GoTtEs!". Pahh!
                        
                        Und dann haben sie dich in den Kerker geworfen. Bauerntrampel ohne Ambition oder Vorstellungskraft!
                        Es wird Zeit, den König über kleiner Flamme zu rösten!
                        
                        Dein Kopf brodelt vor Wut. Erst einmal solltest du wieder zu klaren Gedanken kommen.
                        """
        );
        enterToContinue();
    }

    public static void needHelp() {
        System.out.println("Du hast dich wieder etwas beruhigt. Also, wie ging das Ganze hier noch gleich?");
        System.out.println("Anleitung anzeigen? (j/n)");
        System.out.print("> ");

        input = sc.nextLine().toLowerCase().trim();
        if (input.contains("j")){
            Tutorials.help();
            enterToContinue();
        }
        System.out.println("Du siehst dich noch einmal um: ");
    }

}

class Tutorials extends Story {

    public static void help() {
        System.out.println(
                """
                        
                        =================================================== BEFEHLE ================================================================ 
                        Immer, wenn "+++ Zeit zu handeln! +++" angezeigt wird, können folgende Befehle genutzt werden:
                        
                        Handlung\t\t\t| Eingabe\t\t\t| Effekt
                        ----------------------------------------------------------------------------------------------------------------------------
                        Daimon fragen\t\t| (D)aimon \t\t\t| Bittet das Teufelchen auf deiner Schulter um Hilfe. Tue dies regelmäßig!   
                        \t\t\t\t\t|\t\t\t\t\t|               
                        Person ansprechen\t| (A)nsprechen \t\t| Beginnt ein Gespräch, wenn eine interessierte Person anwesend ist. 
                        \t\t\t\t\t|\t\t\t\t\t|
                        Status anzeigen\t\t| (S)tatus\t\t\t| Zeigt den Stand von Lebens- und Magiepunkten an.
                        Zauberbuch\t\t\t| (Z)auber\t\t\t| Listet die von dir gelernten Zauber auf, nutze sie im Kampf.
                        Codex infernalis\t| (C)odex\t\t\t| Öffnet das Nachschlagewerk zu den dir dienenden Dämonen.
                        Inventar zeigen\t\t| (I)tems\t\t\t| Zeigt an, welche Gegenstände du besitzt.
                        \t\t\t\t\t|\t\t\t\t\t|                        
                        Nimm Item\t\t\t| N.[Item]\t\t\t| Legt einen erreichbaren Gegenstand ins Inventar, z. B. "n.Zellenschlüssel".
                        \t\t\t\t\t|\t\t\t\t\t|
                        Betrachte Item\t\t| B.[Item]\t\t\t| Zeigt eine genauere (B)eschreibung zu Gegenständen im Inventar 
                        \t\t\t\t\t|\t\t\t\t\t| \toder Objekten im aktuellen Raum an.
                        \t\t\t\t\t|\t\t\t\t\t|
                        Verwende Item \t\t| V.[Item]\t\t\t| Verwendet einen Gegenstand IM INVENTAR:                         
                        \t\t\t\t\t|\t\t\t\t\t| \t· Verwende Schlüssel und sonstige Items, um sie im aktuellen Raum anzuwenden.
                        \t\t\t\t\t|\t\t\t\t\t| \t· Verwende Schriftrollen und Formeln, um die darin verborgenen Künste zu erlernen.
                        \t\t\t\t\t|\t\t\t\t\t| \t· Verwende Taschen und Beutel, um die Inhalte auszupacken und ins Inventar zu legen.
                        \t\t\t\t\t|\t\t\t\t\t| \tz. B. "v.Zellenschlüssel", "v.Zauberschriftrolle der üblen Saat", "v.Hexertasche"
                        \t\t\t\t\t|\t\t\t\t\t|
                        Kombiniere Items\t| K.[Item].[Item]\t| Kombiniert zwei Items aus dem Inventar miteinander,
                          \t\t\t\t\t| \t\t\t\t\t| \tz. B. "k.Papier.Stift" -> Maleficarius erhält eine Zeichnung.
                        \t\t\t\t\t| \t\t\t\t\t| \tBraue außerdem Tränke, indem du Alchemiezutaten gleicher Farbe kombinierst.                        
                        \t\t\t\t\t|\t\t\t\t\t|
                        Gehe in Richtung\t| G.[Richtung]\t\t| Versucht, den aktuellen Ort in gewählter Richtung zu verlassen.
                        (Raum wechseln)\t\t| \t\t\t\t\t| \tRichtungen: Nord (N), Ost (O), Süd (S), West (W), hoch (h), runter (r),
                          \t\t\t\t\t| \t\t\t\t\t| \tz. B. "G.Norden" oder einfach "g.n".                        
                        \t\t\t\t\t|\t\t\t\t\t|
                        Raum ansehen\t\t| (R)aum \t\t\t| Zeigt erneut Namen und Beschreibung des aktuellen Raums an.
                        \t\t\t\t\t|\t\t\t\t\t|\s
                        Spielanleitung\t\t| (H)ilfe \t\t\t| Ruft diese Anleitung auf.
                        Kampftutorial\t\t| Kampf \t\t\t| Ruft die Anleitung zum Kampfsystem auf.
                        Rätselhilfe\t\t\t| Tipp \t\t\t\t| Erhalte einen Hinweis, wenn du beim Binden von Dämonen nicht weiterkommst.
                        \t\t\t\t\t|\t\t\t\t\t|
                        Mitwirkende\t\t\t| Credits \t\t\t| Zeigt die Mitwirkenden.
                        Beenden\t\t\t\t| Ende \t\t\t\t| Beendet das Spiel.
                        
                        Groß-/Kleinschreibung wird ignoriert. Copy-paste ist hilfreich.                        
                        ============================================================================================================================
                        """
        );
    }

    public static void helpBattle() {
        System.out.println(
                """
                        ================================================= KAMPFTUTORIAL =================================================
                        Maleficarius lässt einen Dämonen für sich kämpfen und unterstützt ihn mit Hexerei.
                        Zu Beginn des Kampfes beschwörst du einen Dämon. Wenn du am Zug bist, wird eine Liste mit Optionen angezeigt.
                        Oben stehen die Fähigkeiten des Dämons, unter der gestrichelten Linie die Möglichkeiten von Maleficarius.
                        Nach deinem Dämon sind die Gegner an der Reihe. Soll Maleficarius handeln, kann er dies vor dem Dämon tun,
                        ohne dass dessen Zug verfällt. Einen Zauber zu wirken, kostet Manapunkte (MP).
                        
                        Beispiel für eine Kampfsituation:
                        
                        [NAME GEGNERTEAM]
                        Soldat Max\t(53/80 HP) \t\t\t\t\t<--- Name und Lebenspunkte der Gegner
                        Soldat Jörg\t(63/80 HP) – Üble Saat (2)\t<--- Auf Gegner wirkende Zauber und verbleibende Dauer
                        Soldatin Franziska\t(80/80 HP)\t\s
                        
                        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                        
                        [NAME DÄMON] \t(67/100 HP)\t\t<--- Name und Lebenspunkte des aktiven Dämons
                        [1] [Einzelangriff]\t\t\t\t<--- Dämon-Angriff gegen einzelnen Gegner
                        [2] [Flächenangriff]\t\t\t<--- Dämon-Angriff gegen alle Gegner
                        [3] [Selfbuff]\t\t\t\t\t<--- Dämon wirkt eine Fähigkeit auf sich selbst
                        ------------------------------------------------------------------------
                        [4] Hexerei\t\t\t\t\t\t<--- Maleficarius wirkt einen Zauber
                        [5] Beschwören\t\t\t\t\t<--- Maleficarius beschwört einen anderen Dämon
                        >\s
                        
                        Neben ">" gibst du die Zahl der gewünschten Aktion ein.
                        
                        Den meisten konventionellen Angriffen kann mit gewisser Chance ausgewichen werden.
                        Die Flüche eines Hexenmeisters binden sich an die Seele eines Wesens und verfehlen niemals.
                        
                        Jeder Dämon verfügt über drei Fähigkeiten: Einen Angriff gegen einen Gegner,
                        einen Angriff gegen alle Gegner und eine Fähigkeit, die den Dämon selbst verstärkt.
                        
                        Du kannst einen neuen Dämon beschwören, anschließend sind aber zuerst die Gegner an der Reihe.
                        Zu jedem Zeitpunkt kann nur ein Dämon im Kampf sein.
                        
                        Fallen die HP eines Dämons auf 0, kannst du einen neuen beschwören.
                        Nach dem Kampf erhalten besiegte Dämonen 1 HP zurück.
                        Wurden alle deine Dämonen besiegt, ist Maleficarius' Ende gekommen.
                        =======================================================================================================
                        """
        );
    }

    public static void answers() {
        System.out.println("\n================ LÖSUNGEN DER RÄTSEL ================");
        System.out.print("Erster Dämon, ");
        System.out.print(BuildItems.dem01.otherNames + ": \t\t");
        System.out.println(Descriptions.trueNameDem01);
        System.out.print("Zweiter Dämon, ");
        System.out.print(BuildItems.dem02.otherNames + ": \t");
        System.out.println(Descriptions.trueNameDem02);
        System.out.print("Dritter Dämon, ");
        System.out.print("der Abgründige: \t\t\t");
        System.out.println(Descriptions.trueNameDem03);
        System.out.println("=====================================================");
    }

}

