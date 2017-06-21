package nxt.libs.sample;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.robotics.Color;
import nxt.libs.abst.AbstCar;
import nxt.libs.abst.AbstDriver;
import nxt.libs.abst.AbstNavigator;
import nxt.libs.addon.Logger;

/**
 * 単純な左エッジ走行をするためのCar具象クラス
 *
 * @author mmotoki
 */
public class SampleCar extends AbstCar {
    static String[] colorNames = {"Red", "Green", "Blue", "Yellow", "Magenta", "Orange", "White", "Black", "Pink",
            "Gray", "Light gray", "Dark gray", "Cyan"};
    private AbstNavigator nav = new LeftEdgeTracer();
    private AbstDriver driver = new SimpleDriver();

    @Override
    @Deprecated
    public void run() {
        // 初期化処理
        start();


        // 20160509追加
        // ログ記録時のみ必要
        Logger logger = Logger.getInstance();
        logger.start();

        while (checker.getColorID() != 0 && !Button.ESCAPE.isDown()) {
            show();
            nav.decision(checker, driver);
        }

        // 停止処理
        stop(driver);


        // 20170424 追加
        System.out.println("Press ENTER to send logs");
        System.out.println("Press the others to finish");
        int pressedButton = Button.waitForAnyPress();
        // ENTERが押された時だけログ送信
        if (pressedButton == Button.ID_ENTER) {
            logger.stopThread();
            logger.SendLog();
        }
    }

    private void show() {
        Color color = checker.getColor();
        LCD.clear();

        LCD.drawString("Color ID = ", 0, 2); // カラーIDを表示
        int id = color.getColor();
        LCD.drawInt(id, 11, 2);
        LCD.drawString(colorNames[id], 5, 3);

        LCD.drawString("R", 0, 4); // 赤成分を取得
        LCD.drawInt(color.getRed(), 1, 5);

        LCD.drawString("G", 4, 4); // 緑成分を取得
        LCD.drawInt(color.getGreen(), 5, 5);

        LCD.drawString("B", 8, 4); // 青成分を取得
        LCD.drawInt(color.getBlue(), 9, 5);
    }

}
