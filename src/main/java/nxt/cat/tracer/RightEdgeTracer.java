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
//               float manipulate = pid.doPID(checker.getBrightness());
                float manipulate = pid.exec(checker.getBrightness());

                if (manipulate > 0) {
                    driver.changeSpeed(0, -manipulate);
                } else {
                    driver.changeSpeed(manipulate, 0);
                }
                break;
            default:
                driver.goStraight();
        }
    }
}
