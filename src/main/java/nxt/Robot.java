package nxt;

import nxt.cat.CatCar;
import nxt.libs.abst.AbstCar;

public class Robot {
    public static void main(String[] args) {

//        ColorCalibration colorCalibration = new ColorCalibration();
//
//        ColorHTSensor sensor = colorCalibration.calibration();
//
//        System.out.println("Press any button");
//        Button.waitForAnyPress();

        AbstCar car = new CatCar();
        car.run();
    }
}
