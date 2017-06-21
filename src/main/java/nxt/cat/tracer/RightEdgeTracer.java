package nxt.cat.tracer;


import nxt.libs.abst.AbstDriver;
import nxt.libs.abst.AbstNavigator;
import nxt.libs.addon.SensorChecker;

public class RightEdgeTracer extends AbstNavigator {

    @Override
    public void decision(SensorChecker checker, AbstDriver driver) {
        int colorID = checker.getColorID();
        // white
        if (colorID == 6) {
            driver.turnLeft();
        }
        // black
        else if (colorID == 7) {
            driver.turnRight();
        } else {
            driver.start();
        }
    }
}
