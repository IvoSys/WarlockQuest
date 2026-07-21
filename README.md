Hexenmeister Maleficarius will aus dem Kerker ausbrechen und den König stürzen!  
Mein Textadventure mit Dialogen, Rätseln, Kampfsystem, Alchemiesystem und mehr.  
+++ Projekt in Arbeit! +++  
  
Hauptprogramm in WarlockQuest.java  
  
- ASCII: ASCII-Art für Titel usw.  
- Battle: 	Battle-Loop für das Kampfsystem 
- Demon:		Abstrakte Dämon-Klasse und implementierende Unterklassen  
- Encounter:	Fasst Gegner, Strings und Items in Encountern zusammen  
- Enemy:		Abstrakte Gegner-Klasse und implementierende Unterklassen (Soldat, Ritter, Magier …)  
- Item:		Abstrakte Item-Klasse und implementierende Unterklassen (Schlüssel, Trankzutaten, Schriftrollen …)  
- Player:		Attribute und Methoden für den Spieler – Position in Spielwelt, aktuelle Magiepunkte, Arrays für Items und Zauber usw.  
- Room:		Definiert Räume mit Encountern, Türen, Gegenständen usw.  
- Spell:		Abstrakte Zauber-Klasse und implementierende Unterklassen (konkrete Zaubersprüche)  
- Story:		Sammelt Strings für Dialoge, Beschreibungen für Räume und Gegenstände, Tutorials usw.  
- WarlockQuest:	Main-Methode und Game-Loop  
- WorldBuilder:	Instanziiert Objekte aus Klassen (Räume, Items, Gegner …)  