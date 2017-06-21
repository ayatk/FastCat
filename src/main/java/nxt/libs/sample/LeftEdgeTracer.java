package nxt.libs.sample;

import nxt.libs.abst.AbstDriver;
import nxt.libs.abst.AbstNavigator;
import nxt.libs.addon.SensorChecker;

/**
 * 左エッジ走行のためのNavigatorクラス
 *
 * @author mmotoki
 */
public class LeftEdgeTracer extends AbstNavigator {

    @Override
    public void decision(SensorChecker checker, AbstDriver driver) {
        int colorID = checker.getColorID();
        // white
        if (colorID == 6) {
            driver.turnRight();
        }
        // black
        else if (colorID == 7) {
            driver.turnLeft();
        } else {
            driver.start();
        }
    }

}
