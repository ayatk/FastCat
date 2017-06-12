package nxt;

import lejos.nxt.Button;
import nxt.sample.MotorControl;

public class Robot {
    public static void main(String[] args) {
        MotorControl motor = new MotorControl();

        motor.run(400, 500);

        System.out.println("Press any button");
        Button.waitForAnyPress();
    }
}
