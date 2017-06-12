package nxt;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.nxt.addon.ColorHTSensor;
import lejos.robotics.Color;
import lejos.util.Delay;
import nxt.sample.ColorCalibration;

public class Robot {

    static String[] colorNames = {
            "Red", "Green", "Blue", "Yellow", "Magenta", "Orange", "White", "Black",
            "Pink", "Gray", "Light gray", "Dark gray", "Cyan"};

    public static void main(String[] args) {
        ColorCalibration colorCalibration = new ColorCalibration();

        ColorHTSensor sensor = colorCalibration.calibration();

        while (!Button.ESCAPE.isDown()) { // ESCAPE押下待ち

            LCD.clear();
            // カラーIDと名前を表示
            LCD.drawString("Color ID = ", 0, 1);
            int id = sensor.getColorID();
            LCD.drawInt(id, 11, 1);
            LCD.drawString(colorNames[id], 5, 2);

            // 赤成分を表示
            Color color = sensor.getColor();
            LCD.drawString("R", 0, 4);
            LCD.drawInt(color.getRed(), 1, 5);

            // 緑成分を表示
            LCD.drawString("G", 4, 4);
            LCD.drawInt(color.getGreen(), 5, 5);
            // 青成分を表示
            LCD.drawString("B", 8, 4);
            LCD.drawInt(color.getBlue(), 9, 5);

            LCD.refresh();
            Delay.msDelay(200);
        }

        System.out.println("Press any button");
        Button.waitForAnyPress();
    }
}
