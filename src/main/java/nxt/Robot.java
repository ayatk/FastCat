package nxt;

import nxt.cat.CatCar;
import nxt.libs.abst.AbstCar;

public class Robot {
    public static void main(String[] args) {
        AbstCar car = new CatCar();
        car.run();
    }
}
