package nxt.libs.sample;

import lejos.util.Delay;
import nxt.libs.abst.AbstDriver;

/**
 * 単純な走行
 *
 * @author mmotoki
 */
public class SimpleDriver extends AbstDriver {
    private int delay = 200;
    private int speedHigh = 500;
    private int speedNormal = 300;
    private int speedLow = 100;

    @Override
    public void turnLeft() {
        setSpeed(speedNormal, speedLow);
        forward();
        Delay.msDelay(delay);
        stop();
    }

    @Override
    public void turnLeftQuick() {
        setSpeed(speedHigh, speedNormal);
        forward();
        Delay.msDelay(delay);
        stop();
    }

    @Override
    public void turnLeftSlack() {
        setSpeed(speedLow, 0);
        forward();
        Delay.msDelay(delay);
        stop();
    }

    @Override
    public void turnRight() {
        setSpeed(speedLow, speedNormal);
        forward();
        Delay.msDelay(delay);
        stop();
    }

    @Override
    public void turnRightQuick() {
        setSpeed(speedNormal, speedHigh);
        forward();
        Delay.msDelay(delay);
        stop();
    }

    @Override
    public void turnRightSlack() {
        setSpeed(0, speedLow);
        forward();
        Delay.msDelay(delay);
        stop();
    }

    // 20160509追加
    @Override
    public void goStraight() {
        setSpeed(speedNormal);
        forward();
        Delay.msDelay(delay);
        stop();
    }

    @Override
    public void goStraightFast() {
        setSpeed(speedHigh);
        forward();
        Delay.msDelay(delay);
        stop();
    }

    @Override
    public void goStraightSlow() {
        setSpeed(speedLow);
        forward();
        Delay.msDelay(delay);
        stop();
    }

    @Override
    public void start() {
        setSpeed(speedHigh);
        forward();
        Delay.msDelay(delay);
        stop();
    }

}
