package nxt.cat.tracer;

import lejos.robotics.Color;
import nxt.libs.abst.AbstDriver;
import nxt.libs.abst.AbstNavigator;
import nxt.libs.addon.SensorChecker;

public class LeftEdgeTracer extends AbstNavigator {

    @Override
    public void decision(SensorChecker checker, AbstDriver driver) {
        int colorID = checker.getColorID();
        // white
        if (colorID == Color.WHITE || colorID == Color.PINK) {
            driver.turnRight();
        }
        // black
        else if (colorID == Color.BLACK) {
            driver.turnLeft();
        } else {
            driver.start();
        }
    }
}