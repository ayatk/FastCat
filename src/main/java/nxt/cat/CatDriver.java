package nxt.cat;

import nxt.libs.abst.AbstDriver;

/**
 * 単純な走行
 *
 * @author mmotoki
 */
public class CatDriver extends AbstDriver {
    private int speedHigh = 600;
    private int speedNormal = 405;
    private int speedLow = 175;

    @Override
    public void turnLeft() {
        setSpeed(speedNormal, speedLow);
    }

    @Override
    public void turnLeftQuick() {
        setSpeed(speedHigh, speedNormal);
    }

    @Override
    public void turnLeftSlack() {
        setSpeed(speedLow, 0);
    }

    @Override
    public void turnRight() {
        setSpeed(speedLow, speedNormal);
    }

    @Override
    public void turnRightQuick() {
        setSpeed(speedNormal, speedHigh);
    }

    @Override
    public void turnRightSlack() {
        setSpeed(0, speedLow);
    }

    // 20160509追加
    @Override
    public void goStraight() {
        setSpeed(speedNormal);
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
        setSpeed(speedHigh);
        forward();
    }

}
