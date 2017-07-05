package nxt.cat.tracer;


import lejos.robotics.Color;
import nxt.cat.PID;
import nxt.libs.Navigator;
import nxt.libs.abst.AbstDriver;
import nxt.libs.addon.SensorChecker;

public class RightEdgeTracer implements Navigator {

    @Override
    public void decision(SensorChecker checker, AbstDriver driver, PID pid) {
        switch (checker.getColorID()) {
            case Color.BLACK:
            case Color.WHITE:
            case Color.PINK:
                float manipulate = pid.calc(checker.getBrightness());

                if (manipulate > 0) {
                    driver.setSpeed(driver.getBaseSpeed() + manipulate, driver.getBaseSpeed());
                } else {
                    driver.setSpeed(driver.getBaseSpeed(), driver.getBaseSpeed() - manipulate);
                }
                break;
            default:
                driver.goStraight();
        }
    }
}
