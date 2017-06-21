package nxt.cat;

import nxt.libs.abst.AbstDriver;

/**
 * 単純な走行
 *
 * @author mmotoki
 */
public class CatDriver extends AbstDriver {
    private int speedHigh;
    private int speedNormal;
    private int speedLow;

    public CatDriver(int speedHigh, int speedNormal, int speedLow) {
        this.speedHigh = speedHigh;
        this.speedNormal = speedNormal;
        this.speedLow = speedLow;
    }

    @Override
    public void turnLeft() {
        setSpeed(speedNormal, speedLow);
        forward();
    }

    @Override
    public void turnLeftQuick() {
        setSpeed(speedHigh, speedNormal);
        forward();
    }

    @Override
    public void turnLeftSlack() {
        setSpeed(speedLow, 0);
        forward();
    }

    @Override
    public void turnRight() {
        setSpeed(speedLow, speedNormal);
        forward();
    }

    @Override
    public void turnRightQuick() {
        setSpeed(speedNormal, speedHigh);
        forward();
    }

    @Override
    public void turnRightSlack() {
        setSpeed(0, speedLow);
        forward();

    }

    // 20160509追加
    @Override
    public void goStraight() {
        setSpeed(speedNormal);
        forward();
    }

    @Override
    public void goStraightFast() {
        setSpeed(speedHigh);
        forward();
    }

    @Override
    public void goStraightSlow() {
        setSpeed(speedLow);
        forward();
    }

    @Override
    public void start() {
        setSpeed(speedHigh);
        forward();
    }

}
