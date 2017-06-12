package nxt.sample;

import lejos.nxt.Button;

/**
 * Created by ayatk on 2017/06/12.
 */
public class HelloWorld {
    public void run() {
        System.out.println("Press any button");
        Button.waitForAnyPress();
    }
}
