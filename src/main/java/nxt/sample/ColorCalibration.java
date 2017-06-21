package nxt.sample;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorHTSensor;
import lejos.util.Delay;

/**
 * Created by ayatk on 2017/06/12.
 */
public class ColorCalibration {
    public ColorHTSensor calibration() {
        ColorHTSensor sensor = new ColorHTSensor(SensorPort.S3);

        LCD.drawString("Starting calibration...", 0, 1);
        Delay.msDelay(1000);

        LCD.drawString("Black Level", 0, 1);

        Button.ENTER.waitForPress();

        if (sensor.initBlackLevel() == 0) {
            LCD.drawString("OK", 0, 1);
        } else {
            LCD.drawString("No Good...", 0, 1);
        }

        Button.ENTER.waitForPress();

        LCD.drawString("White Level", 0, 1);

        Button.ENTER.waitForPress();

        if (sensor.initWhiteBalance() == 0) {
            LCD.drawString("OK", 0, 1);
        } else {
            LCD.drawString("No Good...", 0, 1);
        }

        Button.ENTER.waitForPress();

        return sensor;
    }
}
