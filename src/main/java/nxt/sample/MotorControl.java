package nxt.sample;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.util.Delay;

/**
 * Created by ayatk on 2017/06/12.
 */
public class MotorControl {
    public void run(int speed, int delay) {
        NXTRegulatedMotor rightWheel = Motor.A;
        NXTRegulatedMotor leftWheel = Motor.C;

        Long start = System.currentTimeMillis();

        rightWheel.setSpeed(speed);
        leftWheel.setSpeed(speed);

        rightWheel.forward();
        leftWheel.backward();

        Delay.msDelay(delay);

        rightWheel.stop();
        leftWheel.stop();

        Long time = System.currentTimeMillis() - start;
        System.out.println("RunTime: " + time + "ms");
    }
}
