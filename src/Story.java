import java.util.Scanner;

public class Story {

    static Scanner sc = new Scanner(System.in);
    static String input = "0";

    static int refuseViolence = 0;
    static String daimonDidNotUnderstand = "DAIMON: \"Was nuschelst du da in deinen Ziegenbart?\" \n";


    //Fähigkeiten von Dämonen mit flavor

    //Kills mit Flavor
}

class Daimon extends Story {

    public static void speak() {
        System.out.println(Player.room.daimon);                                 //Daimon-Kommentar für diesen Raum
        if ((Player.room.puzzleID == -1) && (Player.room.reward != null)) {     //In bestimmten Räumen führt Daimon spezielle Methoden aus
            getKey000();
        }
        if ((Player.room.puzzleID == 12) && (!Player.team.contains(WorldBuilder.dem01))) {
            brainstorm012();
        }
        if ((Player.room.puzzleID == 10)) {
            forAlfred010();
        }
    }

    public static void intro() {
        System.out.println(
                """
                        "Malefiz, alter Halunke, da hast du dir aber was eingebrockt!
                        Kein Wunder, dass ein Griesgram wie du irgendwann im Kerker endet."
                        
                        Daimon, dein persönliches Teufelchen, hockt betont lässig auf deiner Schulter
                        und spielt schelmisch mit seiner Schwanzspitze.
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
                System.out.println("DAIMON: \"Ach, auf einmal hast du's eilig und willst meine Hilfe, was? \nNa gut, bin ja nicht so ein Sauertopf wie du …\" \n\nDaimon schlüpft durch die Gitterstäbe und fingert den Schlüssel aus dem Gürtelring \ndes Wärters, der dies mit verschlafenem Grunzen quittiert. Dann kehrt das Teufelchen \nzu dir zurück, dreht noch einmal um, verknotet dem Wärter die Schnürsenkel, \nhuscht zurück in die Zelle und hält dir den ZELLENSCHLÜSSEL unter die Nase. \nDAIMON: \"Da, nimm!\"\n");
                Player.room.loot.add(Player.room.reward);
                Player.room.reward = null;
                WorldBuilder.castle[0][0][0].notLoot.remove("zellenschlüssel");
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
            case "1": System.out.println("DAIMON: \"Meh, was hab ich erwartet.\"\n"); break;
            case "2": System.out.println("DAIMON: \"Na, das hat doch gar nicht weh getan!\"\n"); break;
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
                        [0]\t (Lieber schweigen)
                        """
        );
        System.out.print("> ");
        input = sc.nextLine().toLowerCase().trim();
        switch (input) {
            case "1":
                System.out.println("DAIMON: \"Na klar, Chef!\"");
                break;
            case "2":
                System.out.println("DAIMON: \"Ach Malefex, weißt du überhaupt, wonach du fragst?\"");
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
            [1] … Alfred haben wir vorhin selbst umgebracht.
            [2] Der Mann hatte recht, Essiggurken und Käsebrot gehören zusammen.
            [0] (Schweigen)
            """);
        Control.inputLine();
        switch (input) {
            case "1": System.out.println("DAIMON: \"Aber er hätte es sicher so gewollt!\""); break;
            case "2":
                System.out.println("DAIMON: \"Ja ja, wie Leberwurst und Butter.\"\n");
                System.out.println("""
                Wähle eine Antwort:
                [1] Du bist wahrhaftig eine Kreatur aus der Hölle.
                [2] Genau.""");
                Control.inputLine();
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
    static String desc000 = "Eine klamme Nische hinter Eisenstangen, gegenüber ein schnarchender Wärter mit einem Schlüssel am Gürtel. Klassiker. \nDie Tür nach Osten steht offen, aber erstmal musst du dich aus der Zelle befreien.";
    static String descSolved000 = "Eine klamme Nische hinter Eisenstangen, gegenüber ein schnarchender Wärter mit einem Schlüssel am Gürtel. Klassiker. \nDie Zellentür nach Osten steht nun offen.";
    static String daimon000 = "DAIMON: \"Bei dem Schnarchen ist's nur eine Frage der Zeit, bis der Wärter die Gitterstäbe selbst durchgesägt hat …\"";
    static String daimonSolved000 = "DAIMON: \"Der Wärter ratzt einfach weiter …\"";
    static String solved000 = "Mit rostigem Knarzen schwingt die Zellentür auf, \ndu kannst nach Osten aus der Zelle heraustreten.\n";
    //Kerker
    static String desc001 = "Der Wachraum liefert guten Blick auf die westliche Zelle – wenn man wach bleibt. Durch die offene Tür im Osten kriecht ein kalter Zug. \nDer Wächter schläft immer noch.";
    static String descSolved001;
    static String daimon001 = "DAIMON: \"Sieh mal zu, dass du dein Zeug wiederbekommst. Die Königsgarde hat dir alles abgenommen, bevor man dich eingebuchtet hat.\"";
    static String daimonSolved001;
    static String solved001 = "";
    //Kellergang
    static String desc002 = "Der Gang führt um die Ecke nach Norden, aus der Ferne hallen Geräusche.";
    static String descSolved002;
    static String daimon002 = "DAIMON: \"Platzhalter in Story-Klasse\"";
    static String daimonSolved002;
    static String solved002 = "Platzhalter in Story-Klasse";
    //Offiziersquartier
    static String desc010 = "Platzhalter in Story-Klasse";
    static String descSolved010;
    static String daimon010 = "Daimon spuckt auf Aufseher Ottos Leiche.\nDAIMON: \"Das war für Alfred!\"";
    static String daimonSolved010;
    static String solved010 = "Platzhalter in Story-Klasse";
    static String enc010Intro;
    static String enc010Outro;
    //Vorratskammer
    static String desc011 = """
            Eine Vielzahl von Gerüchen umgibt dich: Von der Decke hängen verschiedene Würste und Schinken,
            ein angeschnittener Laib Käse liegt auf einem Tisch, daneben einige in Leinen geschlagene Brote.
            Eine dir wohlbekannte Tasche wurde achtlos in die Ecke geworfen. An der Wand sind Fässer mit Bier und Wein aufgestapelt.
            Ein Einmachglas ist mit Essiggurken gefüllt, in einem anderen schwimmt eine einzelne glitschige Kugel,
            und in einer Schale ist eine Handvoll hellblaue Beeren übrig.""";
    static String descSolved011;
    static String daimon011 = "DAIMON: \"Eine Vorratskammer! Nimm alles mit, was nicht niet- und nagelfest ist!\"";
    static String daimonSolved011;
    static String solved011 = "Platzhalter in Story-Klasse";
    //Vorraum (UG)
    static String desc012 = """
            Der Vorraum des Untergeschosses. Im Osten führt hinter einer Gittertür eine breite Steintreppe ins Erdgeschoss – Offiziere tragen einen Schlüssel.
            Im Westen sitzt eine Tür zur "Vorratskammer" in der Wand. Im Norden geht es zur Kantine, aus der das Grölen einiger Wachen hallt.
            """;
    static String descSolved012;
    static String daimon012 = "DAIMON: \"Schau dir meine Arme an – dünn wie Zahnstocher! Ganz zu schweigen von den welken Lauchstangen, die dir aus den Schultern wachsen. \nWenn du den Wachen ohne Argumentationsverstärker vor den Knüppel kommst, machen sie Hexerkompott aus dir.\"";
    static String daimonB012 = "DAIMON: \"Jetzt hast du endlich SO einen Dämon. Also Mal, was hält dich noch – grau ist alle Theorie!\"";
    static String daimonSolved012;
    static String solved012 = "Platzhalter in Story-Klasse";
    static String enc012Intro;
    static String enc012Outro;
    //Waschraum
    static String desc020 = "Platzhalter in Story-Klasse";
    static String descSolved020;
    static String daimon020 = "DAIMON: \"Platzhalter in Story-Klasse\"";
    static String daimonSolved020;
    static String solved020 = "Platzhalter in Story-Klasse";
    //Schlafsaal
    static String desc021 = "Platzhalter in Story-Klasse";
    static String descSolved021;
    static String daimon021 = "DAIMON: \"Platzhalter in Story-Klasse\"";
    static String daimonSolved021;
    static String solved021 = "Platzhalter in Story-Klasse";
    //Wachkantine
    static String desc022 = "Speise- und Aufenthaltsraum der Wachbelegschaft. Die langen Tische sind spartanisch gedeckt, \nnur auf einem lässt ein Blümchen in einem halbvollen Wassergals den Kopf hängen. \nÜber dem glimmenden Kaminfeuer hängt ein großer Topf.";
    static String descSolved022;
    static String daimon022 = "DAIMON: \"Du solltest zusehen, deinen Dämon zu heilen, bevor es weitergeht. \nAlso nicht mich – den mit der großen Axt.\"";
    static String daimonSolved022;
    static String solved022 = "Platzhalter in Story-Klasse";
    static String enc022Intro = "Eine der Wachen hält ein Stück auseinandergerolltes Pergament in der Hand und liest wild gestikulierend daraus vor, \ndie anderen kichern rotnasig. Dramatisch reckt der Wächter seine Faust gen Zimmerdecke, während er eine ausgedachte Zauberformel rezitiert \nund seine Stimme immer höher schraubt, als würde sie sich vor Aufregung überschlagen. \nDabei wirkt er irgendwie wie … du! Die anderen Wachen brechen in haltloses Gelächter aus. \nAngesäuert stößt du die Tür auf, drei Augenpaare wenden sich überrascht zu dir um. ";
    static String enc022Outro = "Röchelnd gehen die Wachen zu Boden. Im nächsten Leben wird ihnen das eine Lehre sein. Du sammelst die Schriftrolle auf.";

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
    static String desc222 = "*** Im Raum westlich werden Encounter getestet ***";
    static String descSolved222;
    static String daimon222 = "DAIMON: \"Platzhalter unten in Story-Klasse\"";
    static String daimonSolved222;
    static String solved222 = "Platzhalter unten in Story-Klasse";
    //endregion

    //Items mit langen Beschreibungen
    static String bookBlackArtsDesc =
            """
                    Das Standardwerk über Hexerei, Alchemie und Dämonologie in drei Bänden, verfasst von Meister Maleficarius Liebwerk:
                    […] Zauber werden mittels Schriftrollen erlernt und können bis zur geistigen Erschöpfung gewirkt werden.
                    […] Tränke werden mit Verabreichung verbraucht und entfalten unvergleichliche Wirkungen, selbst bei Dämonen.
                    […] Spricht man den wahren Namen eines Dämonen deulich aus, zwingt man ihn in seinen Dienst. Doch Obacht, er wird dies nicht schätzen!""";

    //Rätsel Dämonen
    static String riddleDem00 = """
            Diesen okkulten Text hast du vor einer Weile schon entschlüsselt.
            Der wahre Name des Daimon lautet:
            \033[3mGenius Daimonion, Spritus familiaris\033[0m
            
            DAIMON: "Nooohh, Malle, ich bin doch schon bei dir!"
            
            … ob es so eine gute Idee war, diesen Plagegeist zu beschwören?
            """;

    static String riddleDem01 = """
            Du hörst es hallen, das schreckliche Gebrüll aus den Gängen.
            Wenn das Ungeheuer dich in die Irre führt, findest du dann noch den Weg zurück?
            Schau hinab und suche den Pflasterstein, der vorher kam, immer ein Schritt nach dem anderen:
            ;ompzsitpd. e+zrmfrt E#vjzrt;
            """;
    static String trueNameDem01 = "Minotauros, wütender Wächter";

    static String riddleDem02 = "";
    static String trueNameDem02 = "Efreet";

    static String riddleDem03 = """
            Die Formel enthält nichts als einen tiefschwarzen Tintenklecks.
            
            ⠀⠀⠀⠀⠀⠀⠀         ⠀⠀⠀\u001B[30m\u001B[40m⢀⣀⣤⣤⣤⣤⣴⣤⣤⣄⡀\u001B[0m⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀\u001B[30m\u001B[40m⣀⣴⣾⠿⠛⠋⠉⠁⠀⠀⠀⠈⠙⠻⢷⣦⡀\u001B[0m⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀\u001B[30m\u001B[40m⣤⣾⡿⠋⠁⠀⣠⣶⣿⡿⢿⣷⣦⡀⠀⠀⠀⠙⠿⣦⣀\u001B[0m⠀⠀⠀⠀
                    ⠀⠀\u001B[30m\u001B[40m⢀⣴⣿⡿⠋⠀⠀⢀⣼Abaddon,⣿⡆⠀⠀⠀⠀⢻⣿⣷⣶⣄\u001B[0m⠀
                    ⠀\u001B[30m\u001B[40m⣴⣿⣿⠋⠀⠀⠀⠀⠸ Engel des ⣿⠀⠀⠀⠐⡄⡌⢻⣿⣿⡷\u001B[0m
                    \u001B[30m\u001B[40m⢸⣿⣿⠃⢂⡋⠄⠀⠀⠀⢿ Abgrunds⠏⠀⠀⠀⠀⢦⣷⣿⠿⠛⠁\u001B[0m
                    ⠀\u001B[30m\u001B[40m⠙⠿⢾⣤⡈⠙⠂⢤⢀⠀⠙⠿⢿⣿⣿⡿⠟⠁⠀⣀⣀⣤⣶⠟⠋⠁\u001B[0m⠀⠀⠀
                    ⠀⠀⠀⠀\u001B[30m\u001B[40m⠈⠙⠿⣾⣠⣆⣅⣀⣠⣄⣤⣴⣶⣾⣽⢿⠿⠟⠋\u001B[0m⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀\u001B[30m\u001B[40m⠉⠙⠛⠛⠙⠋⠉⠉\u001B[0m⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            
            Du fühlst dich beobachtet.
            
            """;

    static String trueNameDem03 = "Abaddon, Engel des Abgrunds";

    //Rätsel Zauber
    static String riddleBloodletting = "PLATZHALTER: Wie lautet die Lösung?";
    static String formulaBloodletting = "Aderlass";

    static String riddleDoom = "PLATZHALTER: Wie lautet die Lösung?";
    static String formulaDoom = "Untergang";

    static String riddleIronMaiden = "PLATZHALTER: Wie lautet die Lösung?";
    static String formulaIronMaiden = "Eiserne Jungfrau";

    static String riddleLifeline = "PLATZHALTER: Wie lautet die Lösung?";
    static String formulaLifeline = "Lebenslinie";

    static String riddleSoulreaper = "PLATZHALTER: Wie lautet die Lösung?";
    static String formulaSoulreaper = "Avada Kedavra";

    static String riddleViciousSeed = "PLATZHALTER: Wie lautet die Lösung?";
    static String formulaViciousSeed = "ÜbleSaat";

}

class Dialogue extends Story {

    public static void speak() {
        if (!Player.room.hasDialogue)
            System.out.println("Niemand da.");
        else {
            switch (Player.room.puzzleID) {
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
        if (!Player.inv.contains(WorldBuilder.cheeseBread) && !Player.inv.contains(WorldBuilder.sandwich)) {
            System.out.println("STIMME HINTER DER TÜR: \"Alfred? ALFRED! Schleichst du da draußen herum?");
            System.out.println(
                    """
                            Wähle eine Antwort:
                            [1]\t Nein.
                            [2]\t … ja?
                            [0]\t (Lieber schweigen)"""
            );
            Control.inputLine();
            switch (input) {
                case "1": System.out.println("STIMME HINTER DER TÜR: \"Alfred, du Witzbold. Bring mir lieber mein mittägliches Käsebrot. \nUnd zwar WIE ÜBRLICH, und ein wenig zackig!\""); break;
                case "2": System.out.println("STIMME HINTER DER TÜR: \"Was Alfred, bist du dir nicht mal deiner eigenen Identität sicher? \nSei nicht so ein Waschlappen und bring mir mein mittägliches Käsebrot. Und zwar WIE ÜBRLICH, und ein wenig zackig!\""); break;
                case "0": System.out.println("STIMME HINTER DER TÜR: \"Alfred? Aaaaalfred!\""); System.out.println("Du entfernst dich."); break;
                default: System.out.println("STIMME HINTER DER TÜR: \"Alfred, mit dem Herumgedruckse wird das noch länger nichts mit der Beförderung. \nSei nicht so ein Waschlappen und bring mir mein mittägliches Käsebrot. Und zwar WIE ÜBRLICH, und ein wenig zackig!\""); break;
            }
        }
        else if (Player.inv.contains(WorldBuilder.cheeseBread)) {
            System.out.println("STIMME HINTER DER TÜR: \"Alfred? Hast du endlich mein Käsebrot? Hast du's auch richtig gemacht?");
            System.out.println(
                    """
                            Wähle eine Antwort:
                            [1]\t Nein.
                            [2]\t Einmal Käsebrot – mit Käse. Und Brot. Was könnte man da falsch machen?
                            [0]\t (Lieber schweigen)"""
            );
            Control.inputLine();
            switch (input) {
                case "1": System.out.println("STIMME HINTER DER TÜR: \"Dann mach mal hinne, Alfred!"); break;
                case "2": System.out.println("STIMME HINTER DER TÜR: \"Man könnte zum Beispiel die Essiggurke vergessen, Alfred. \nMan könnte sie sogar STÄNDIG vergessen, wie du, Alfred. \nDeshalb schiebst du nach sechs Monaten auch immer noch Wachdienst. Und selbst den kriegst du nur, weil die Gefängniszellen von alleine zu bleiben, Alfred. \nUnd jetzt leg verdammt noch mal Essiggurke auf mein Käsebrot!\""); System.out.println("\nDAIMON: \"Uff, der ist ja fast so ein schlechter Chef wie du, Mal.\""); break;
                case "0": System.out.println("STIMME HINTER DER TÜR: \"Alfred? Aaaaalfred!\""); System.out.println("Du entfernst dich."); break;
                default: System.out.println("STIMME HINTER DER TÜR: \"Alfred, mit dem Herumgedruckse wird das noch länger nichts mit der Beförderung. \nSei nicht so ein Waschlappen und bring mir mein mittägliches Käsebrot. Und zwar WIE ÜBRLICH, und ein wenig zackig!\""); break;
            }
        }

        else if (Player.inv.contains(WorldBuilder.sandwich)) {
            System.out.println("STIMME HINTER DER TÜR: \"Alfred? Hast du endlich mein Käsebrot? Hast du's auch richtig gemacht?");
            System.out.println(
                    """
                            Wähle eine Antwort:
                            [1]\t Nein.
                            [2]\t Einmal Käsebrot mit Essiggurke für den Chef.
                            [0]\t (Lieber schweigen)"""
            );
            Control.inputLine();
            switch (input) {
                case "1":
                    System.out.println("STIMME HINTER DER TÜR: \"Dann mach mal hinne, Alfred!");
                    break;
                case "2":
                    WorldBuilder.castle[0][2][0].south = true;
                    Item.consumeItem(WorldBuilder.sandwich);
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
                        """
        );
    }

    public static void needHelp() {
        System.out.println("Dein Kopf brodelt vor Wut. Du solltest erst einmal wieder zu klaren Gedanken kommen. Wie ging das Ganze hier noch gleich?");
        System.out.println("Anleitung anzeigen? (j/n)");
        System.out.print("> ");
    }

}

class Tutorials extends Story {

    public static void help() {
        System.out.println(
                """
                        
                        =================================================== BEFEHLE ================================================================                        
                        Immer, wenn "Zeit zu handeln!" angezeigt wird, können folgende Befehle genutzt werden:
                        
                        Handlung\t\t\t| Eingabe\t\t\t| Effekt
                        ----------------------------------------------------------------------------------------------------------------------------
                        Daimon fragen\t\t| (D)aimon \t\t\t| Bittet das Teufelchen auf deiner Schulter um Hilfe. Tue dies regelmäßig!                        
                        Sprechen\t\t\t| (S)prechen \t\t| Beginnt ein Gespräch mit einer Person im Raum, falls interessiert. 
                        \t\t\t\t\t|\t\t\t\t\t|
                        Inventar zeigen\t\t| (I)tems\t\t\t| Zeigt an, welche Gegenstände du besitzt (ohne alchemistische Zutaten).                        
                        Nimm Item\t\t\t| N.[Item]\t\t\t| Legt einen erreichbaren Gegenstand ins Inventar, z. B. "n.Zellenschlüssel".
                        Betrachte Item\t\t| B.[Item]\t\t\t| Zeigt eine genauere (B)eschreibung zu einem Gegenstand im Inventar an.
                        Verwende Item \t\t| V.[Item]\t\t\t| Verwendet einen Gegenstand im Inventar, z. B. "v.Zellenschlüssel".
                        \t\t\t\t\t|\t\t\t\t\t| \tVerwende Zauberschriftrollen, um einen neuen Zauber zu lernen.
                        \t\t\t\t\t|\t\t\t\t\t| \tVerwende Beschwörungsformeln, um einen Dämon in deinen Dienst zu zwingen.
                        \t\t\t\t\t|\t\t\t\t\t| \tVerwende Taschen oder Bündel, um die enthaltenen Gegenstände hervorzuholen.
                        \t\t\t\t\t|\t\t\t\t\t| \tVerwende sonstige Items (Schlüssel usw.), um sie im aktuellen Raum anzuwenden.
                        Kombiniere Items\t| K.[Item].[Item]\t| Kombiniert zwei Items aus dem Inventar miteinander,
                          ("combine")\t\t| oder\t\t\t\t| \tz. B. "k.blaue Farbe.gelbe Farbe" -> Maleficarius erhält grüne Farbe.
                        \t\t\t\t\t| C.[Item].[Item]\t| \tAuf gleiche Weise kannst du aus Alchemiezutaten Tränke brauen:
                        \t\t\t\t\t|\t\t\t\t\t| \tKombiniere rote Zutaten für Heiltränke, blaue für Manatränke.
                        \t\t\t\t\t|\t\t\t\t\t|
                        Gehe in Richtung\t| G.[Richtung]\t\t| Versucht, den aktuellen Ort in gewählter Richtung zu verlassen.
                          (Raum wechseln)\t| oder\t\t\t\t| \tRichtungen: Nord (N), Ost (O), Süd (S), West (W), hoch (h), runter (r),
                          ("move")\t\t\t| M.[Richtung]\t\t| \tz. B. "G.Norden" oder einfach "g.n" / "m.n".
                        \t\t\t\t\t|\t\t\t\t\t| \tJedes Stockwerk des Schlosses besteht aus 3x3 Räumen.
                        \t\t\t\t\t|\t\t\t\t\t|
                        Status anzeigen\t\t| Status\t\t\t| Zeigt den Stand von Lebens- und Magiepunkten an.
                        Raum ansehen\t\t| (R)aum \t\t\t| Zeigt erneut Namen und Beschreibung des aktuellen Raums an.
                        Zauberbuch\t\t\t| (Z)auber\t\t\t| Listet die von dir gelernten Zauber auf, nutze sie im Kampf.
                        Alchemiezutaten\t\t| (A)lchemie\t\t| Zeigt deine alchemistischen Zutaten an, braue Tränke aus ihnen.
                        Trankgurt\t\t\t| (T)ränke\t\t\t| Zeigt deine Tränke an, nutze sie AUSSERHALB des Kampfes per "v.[Trank]".
                        Dämonen-Kompendium\t| Dämonen\t\t\t| Zeigt nähere Informationen zu den von dir gebundenen Dämonen an.
                        \t\t\t\t\t|\t\t\t\t\t|\s
                        Spielanleitung\t\t| (H)ilfe \t\t\t| Ruft diese Anleitung auf.
                        Kampftutorial\t\t| Kampf \t\t\t| Ruft die Anleitung zum Kampfsystem auf.
                        Rätselhilfe\t\t\t| Rätsel \t\t\t| Wenn du beim Lernen von Zaubern oder Binden von Dämonen nicht weiterkommst.
                        \t\t\t\t\t|\t\t\t\t\t|\s
                        Mitwirkende\t\t\t| Credits \t\t\t| Zeigt die Mitwirkenden.
                        Beenden\t\t\t\t| Ende \t\t\t\t| Beendet das Spiel.
                        
                        Groß-/Kleinschreibung wird ignoriert, außer bei Zauber- und Beschwörungsformeln. Copy-paste ist hilfreich.
                        Merkhilfe: Die mehrteiligen Befehle (mit ".") werden über die Tasten C bis M in der unteren Tastaturzeile angesteuert.
                        ============================================================================================================================
                        """
        );
    }

    public static void hints() {
        System.out.println(
                """
                        ============================================ HINWEISE ZU RÄTSELN ==============================================
                        ==============================================================================================================="""
        );
    }

    public static void answers() {
        System.out.println("\n======================================= LÖSUNGEN DER RÄTSEL =======================================");
        System.out.print("DÄMONEN\n");
        System.out.print(WorldBuilder.dem01.name + ": \t");
        System.out.println(Descriptions.trueNameDem01);
        System.out.print(WorldBuilder.dem02.name + ": \t");
        System.out.println(Descriptions.trueNameDem02);
        System.out.print(WorldBuilder.dem03.name + ": \t");
        System.out.println(Descriptions.trueNameDem03);
        System.out.println();
        System.out.print("ZAUBER");
        System.out.print(WorldBuilder.bloodletting.name + ": \t");
        System.out.println(Descriptions.formulaBloodletting);
        System.out.print(WorldBuilder.ironMaiden.name + ": \t");
        System.out.println(Descriptions.formulaIronMaiden);
        System.out.print(WorldBuilder.lifeline.name + ": \t");
        System.out.println(Descriptions.formulaLifeline);
        System.out.print(WorldBuilder.soulreaper.name + ": \t");
        System.out.println(Descriptions.formulaSoulreaper);
        System.out.print(WorldBuilder.viciousSeed.name + ": \t");
        System.out.println(Descriptions.formulaViciousSeed);
        System.out.print(WorldBuilder.doom.name + ": \t");
        System.out.println(Descriptions.formulaDoom);
        System.out.println("\n===================================================================================================");
    }

    public static void helpBattle() {
        System.out.println(
                """
                        ================================================= KAMPFTUTORIAL =================================================
                        Maleficarius lässt einen Dämonen für sich kämpfen und unterstützt ihn mit Hexerei.
                        Zu Beginn des Kampfes beschwörst du einen Dämon. Wenn du am Zug bist, wird eine Liste mit Optionen angezeigt.
                        Oben stehen die Fähigkeiten des Dämons, unter der gestrichelten Linie die Möglichkeiten von Maleficarius.
                        Nach deinem Dämon sind die Gegner an der Reihe. Soll Maleficarius handeln, kann er dies vor dem Dämon tun,
                        ohne dass dessen Zug verfällt. Einen Zauber zu wirken, kostet MP.
                        
                        Beispiel für eine Kampfsituation:
                        
                        [NAME GEGNERTEAM]
                        Soldat Max\t(53/80 HP) \t\t\t\t\t<--- Name und HP der Gegner
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
                        
                        Den meisten Angriffen (Schwertern, Pfeilen, magischen Projektilen) kann mit gewisser Chance ausgewichen werden.
                        Die Flüche eines Hexenmeisters binden sich an die Seele eines Lebewesens und verfehlen niemals.
                        
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

}

