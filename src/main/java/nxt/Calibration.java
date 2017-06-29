package nxt;


import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorHTSensor;
import lejos.util.Delay;

public class Calibration {
    public static void main(String[] args) {
        ColorHTSensor sensor = new ColorHTSensor(SensorPort.S3);

        LCD.drawString("Starting calibration...", 0, 1);
        Delay.msDelay(1000);
        LCD.clear();

        LCD.drawString("Black Level ", 0, 1);

        Button.ENTER.waitForPress();

        if (sensor.initBlackLevel() == 0) {
            LCD.drawString("OK", 0, 2);
        } else {
            LCD.drawString("No Good...", 0, 2);
        }

        LCD.drawString("Press any button...", 0, 4);

        Button.ENTER.waitForPress();

        LCD.clear();
        LCD.drawString("White Level", 0, 1);

        Button.ENTER.waitForPress();

        if (sensor.initWhiteBalance() == 0) {
            LCD.drawString("OK", 0, 2);
        } else {
            LCD.drawString("No Good...", 0, 2);
        }

        LCD.drawString("Press any button...", 0, 4);

        Button.ENTER.waitForPress();

        LCD.clear();
    }
}
