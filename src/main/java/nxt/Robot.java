package nxt;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class Robot {
    public static void main(String[] args) {
        NXTRegulatedMotor rightWheel = Motor.A;
        NXTRegulatedMotor leftWheel = Motor.C;


        int speed = 300;

        rightWheel.setSpeed(speed);
        leftWheel.setSpeed(speed);
        rightWheel.forward();
        leftWheel.forward();

        System.out.println("Press any button");
        Button.waitForAnyPress();
    }
}
