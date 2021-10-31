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
            int numberOfStepsForCurrentThread = NUMBER_OF_STEPS - i;
            buildThread(numberOfStepsForCurrentThread);
        }
    }

    private void buildThread(int depthOfThread) {
        for (int i = 0; i < depthOfThread; i++) {
            bouncer.move();
            bouncer.paintField(FieldColor.BLUE);
        }
        turnAround();
        for (int i = 0; i < depthOfThread; i++) {
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

    private void returnToOriginalPosition() {
        turnAround();
        climbDownStairs();
        moveToWallAndTurnAround();
    }

    private void climbDownStairs() {
        for (int i = 0; i < NUMBER_OF_STEPS; i++) {
            climbDownStep();
        }
    }
    
    private void climbDownStep() {
        bouncer.move();
        bouncer.turnLeft();
        bouncer.move();
        turnRight();
    }

    private void moveToWallAndTurnAround() {
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
        turnAround();
    }

    private void turnAround() {
        bouncer.turnLeft();
        bouncer.turnLeft();
    }

    private void turnRight() {
        turnAround();
        bouncer.turnLeft();
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("BouncerBautEineTreppe");
    }
}