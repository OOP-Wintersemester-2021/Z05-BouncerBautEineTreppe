import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.apps.BouncerLauncher;
import de.ur.mi.bouncer.world.fields.FieldColor;


/*
 * In diesem Programm soll Bouncer am östlichen Ende der Karte, ganz unten am Kartenrand,
 * eine blaue Treppe bauen. Die Treppe soll fünf Felder hoch sein. Jede Stufe ist genau ein
 * Feld breit. Bouncer soll ganz am Ende zu seiner Ausgangsposition zurückkehren. Alle Arbeits-
 * schritte, die Bouncer für das Bauen der Treppe mehr als einmal durchführen muss, sollen,
 * mit entsprechenden Parametern, als Methoden abgebildet werden.
 */
public class BouncerBautEineTreppe extends BouncerApp {

    private static final int NUMBER_OF_STEPS = 4;

    @Override
    public void bounce() {
        loadMap("BuildingSteps");
        goToStartPosition();
        buildStairs();
        returnToOriginalPosition();
    }

    private void goToStartPosition() {
        moveToWallAndTurnAround();
        for (int i = 0; i < NUMBER_OF_STEPS; i++) {
            bouncer.move();
        }
        turnAround();
    }

    private void buildStairs() {
        /*
         * Diese for-Schleife können wir in Zukunft auch eleganter und lesbarer Gestalten,
         * sobald wir lernen, solche Kontrollstrukturen "rückwärts" laufen zu lassen. In
         * dieser Lösung berechnen wir die Anzahl der zu bauenden Treppenteil aus der Höhe
         * der (quadratischen) Treppe und der aktuellen Iteration der Schleife.
         */
        for (int i = 0; i < NUMBER_OF_STEPS; i++) {
            int numberOfStepsForCurrentTread = NUMBER_OF_STEPS - i;
            buildTread(numberOfStepsForCurrentTread);
        }
    }

    private void buildTread(int depthOfTread) {
        for (int i = 0; i < depthOfTread; i++) {
            bouncer.move();
            bouncer.paintField(FieldColor.BLUE);
        }
        turnAround();
        for (int i = 0; i < depthOfTread; i++) {
            bouncer.move();
        }
        turnAround();
        climbUpStep();
    }

    private void climbUpStep() {
        bouncer.turnLeft();
        bouncer.move();
        turnRight();
        bouncer.move();
    }

    /**
     * Bouncer kehrt von der obersten Stufe der Treppe zu der Position zurück, auf der er zu Beginn des Programms stand
     * <p>
     * Vorbedingung: Bouncer steht auf der obersten Stufe der Treppe und blickt nach Westen
     * Ergebnis: Bouncer steht auf genau der gleichen Position wie zu Beginn des Programms und blickt erneut nach Osten
     */
    private void returnToOriginalPosition() {
        turnAround();
        climbDownStairs();
        moveToWallAndTurnAround();
    }

    /*
     * Bouncer klettert alle Stufen der Treppe hinab
     *
     * Vorbedingung: Bouncer steht auf der obersten Stufe der Treppe und blickt nach Westen
     * Ergebnis: Bouncer steht vor der ersten Stufe der Treppe und blickt nach Westen
     */
    private void climbDownStairs() {
        for (int i = 0; i < NUMBER_OF_STEPS; i++) {
            climbDownStep();
        }
    }

    /*
     * Bouncer kletter eine Stufe auf der Treppe nach unten
     *
     * Vorbedingung: Bouncer steht auf einer genau ein Feld breiten Stufe der Treppe und blickt nach Westen
     * zum Anfang der Treppe.
     * Ergebnis: Bouncer steht auf der Stufe direkt unter der, auf der er initial gestanden hat und blickt nach Westen.
     */
    private void climbDownStep() {
        bouncer.move();
        bouncer.turnLeft();
        bouncer.move();
        turnRight();
    }

    /*
     * Bouncer bewegt sich geradeaus, bis er an ein Hindernis stößt
     *
     * Vorbedingung: -
     * Ergebnis: Bouncer hat sich, ausgehend von seiner Startpostion, solange in seine ursprüngliche Blickrichtung
     * bewegt, bis auf dem Feld direkt vor ihm ein Hindernis aufgetaucht ist.
     */
    private void moveToWallAndTurnAround() {
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
        turnAround();
    }

    /*
     * Bouncer dreht sich um
     *
     * Vorbedingung: -
     * Ergebnis: Bouncer hat sich, ausgehend von seiner ursprünglichen Position, um 180° nach links gedreht
     */
    private void turnAround() {
        bouncer.turnLeft();
        bouncer.turnLeft();
    }

    /*
     * Bouncer dreht sich nach rechts
     *
     * Vorbedingung: -
     * Ergebnis: Bouncer hat sich, ausgehend von seiner ursprünglichen Position, 270° nach links gedreht
     */
    private void turnRight() {
        turnAround();
        bouncer.turnLeft();
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("BouncerBautEineTreppe");
    }
}