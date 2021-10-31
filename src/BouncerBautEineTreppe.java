import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.apps.BouncerLauncher;


/*
 * In diesem Programm soll Bouncer am östlichen Ende der Karte, ganz unten am Kartenrand,
 * eine blaue Treppe bauen. Die Treppe soll fünf Felder hoch sein. Jede Stufe ist genau ein
 * Feld breit. Bouncer soll ganz am Ende zu seiner Ausgangsposition zurückkehren. Alle Arbeits-
 * schritte, die Bouncer für das Bauen der Treppe mehr als einmal durchführen muss, sollen,
 * mit entsprechenden Parametern, als Methoden abgebildet werden.
 */
public class BouncerBautEineTreppe extends BouncerApp {

    @Override
    public void bounce() {
        loadMap("BuildingSteps");
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("BouncerBautEineTreppe");
    }
}