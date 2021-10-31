import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.apps.BouncerLauncher;


public class BouncerBautEineTreppe extends BouncerApp {

    @Override
    public void bounce() {
        loadMap("BuildingSteps");
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("BouncerBautEineTreppe");
    }
}