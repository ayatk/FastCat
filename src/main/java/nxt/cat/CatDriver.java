package nxt.cat;

import nxt.libs.abst.AbstDriver;

/**
 * 単純な走行
 *
 * @author mmotoki
 */
public class CatDriver extends AbstDriver {
    private float speedHigh = (float) (getBaseSpeed() * 1.5);
    private float speedLow = (float) (getBaseSpeed() * 0.43);

    @Override
    public void turnLeft() {
        setSpeed(getBaseSpeed(), speedLow);
    }

    @Override
    public void turnLeftQuick() {
        setSpeed(speedHigh, getBaseSpeed());
    }

    @Override
    public void turnLeftSlack() {
        setSpeed(speedLow, 0);
    }

    @Override
    public void turnRight() {
        setSpeed(speedLow, getBaseSpeed());
    }

    @Override
    public void turnRightQuick() {
        setSpeed(getBaseSpeed(), speedHigh);
    }

    @Override
    public void turnRightSlack() {
        setSpeed(0, speedLow);
    }

    // 20160509追加
    @Override
    public void goStraight() {
        setSpeed(getBaseSpeed());
    }

    @Override
    public void goStraightFast() {
        setSpeed(speedHigh);
    }

    @Override
    public void goStraightSlow() {
        setSpeed(speedLow);
    }

    @Override
    public void start() {
        setSpeed(getBaseSpeed());
        forward();
    }

}
