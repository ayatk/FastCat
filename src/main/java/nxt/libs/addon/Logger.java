package nxt.libs.addon;

import lejos.nxt.Motor;
import lejos.nxt.comm.USB;
import lejos.robotics.Color;
import lejos.util.LogColumn;
import lejos.util.NXTDataLogger;
import nxt.cat.PID;

import java.io.IOException;

/**
 * ロボットの動作ログを記録するスレッドのクラス
 * Singletonパターンを使うので，一つしかインスタンスを作成できない
 * 使用法：
 * <ol>
 * <li> getInstance()でインスタンスを取得する
 * <li> 1.で取得したインスタンスに対し，start()を実行し，ログ記録開始
 * <li> スレッドの実行を終了するには，stopThread()を実行
 * </ol>
 *
 * @author mmotoki
 */
public class Logger extends Thread {
    /**
     * Singletonパターンとするための，唯一のインスタンス
     */
    private static Logger logger = new Logger();
    /**
     * ロガーの実体
     */
    NXTDataLogger nxtLogger = new NXTDataLogger();
    /**
     * スレッドがアクティブならtrue
     * falseにするとスレッドは停止
     */
    private boolean isActive = true;

    /**
     * ログ記録間隔 (ms)
     * setIntervalメソッドでも設定可能
     */
    private int interval = 50;

    private PID pid;

    /**
     * ログの列の設定
     */
    private LogColumn[] logColumns = {
            new LogColumn("Color", LogColumn.DT_INTEGER),
            new LogColumn("R", LogColumn.DT_INTEGER),
            new LogColumn("G", LogColumn.DT_INTEGER),
            new LogColumn("B", LogColumn.DT_INTEGER),
            new LogColumn("Brightness", LogColumn.DT_INTEGER),
            new LogColumn("Diff", LogColumn.DT_INTEGER),
            new LogColumn("P", LogColumn.DT_FLOAT),
            new LogColumn("I", LogColumn.DT_FLOAT),
            new LogColumn("D", LogColumn.DT_FLOAT),
            new LogColumn("Derivative", LogColumn.DT_FLOAT),
            new LogColumn("Integral", LogColumn.DT_FLOAT),
            new LogColumn("Manipulate", LogColumn.DT_FLOAT),
            new LogColumn("Dt", LogColumn.DT_LONG),
            new LogColumn("RSpeed", LogColumn.DT_INTEGER),
            new LogColumn("LSpeed", LogColumn.DT_INTEGER)
    };

    /**
     * インスタンスの取得
     *
     * @return 唯一のインスタンス
     */
    private SensorChecker checker = SensorChecker.getInstance();


    /**
     * Singleton パターンとするために，コンストラクタはprivateとなっている
     */
    private Logger() {
    }

    public void setPid(PID pid) {
        this.pid = pid;
    }

    /**
     * インスタンスの取得
     *
     * @return 唯一のインスタンス
     */
    public static Logger getInstance() {
        return logger;
    }

    public NXTDataLogger getNXTLogger() {
        return nxtLogger;
    }

    /**
     * ログ記録スレッドの実行停止
     */
    public void stopThread() {
        this.isActive = false;
    }

    /**
     * ログ間隔時間の設定
     *
     * @param interval ログ間隔時間(ms)
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * ログ記録スレッドの開始
     */
    @Override
    public void run() {
        isActive = true;
        // ログ記録開始
        nxtLogger.startCachingLog();
        // ログの列の設定
        nxtLogger.setColumns(logColumns);
        while (isActive) {
            Color color = checker.getColor();
            nxtLogger.writeLog(color.getColor());
            nxtLogger.writeLog(color.getRed());
            nxtLogger.writeLog(color.getGreen());
            nxtLogger.writeLog(color.getBlue());
            nxtLogger.writeLog(Math.max(Math.max(color.getRed(), color.getGreen()), color.getBlue()));
            nxtLogger.writeLog(pid.getDiff());
            nxtLogger.writeLog(pid.getP());
            nxtLogger.writeLog(pid.getI());
            nxtLogger.writeLog(pid.getD());
            nxtLogger.writeLog(pid.getDerivative());
            nxtLogger.writeLog(pid.getIntegral());
            nxtLogger.writeLog(pid.getManipulate());
            nxtLogger.writeLog(pid.getDt());
            nxtLogger.writeLog(Motor.A.getSpeed());
            nxtLogger.writeLog(Motor.C.getSpeed());
            nxtLogger.finishLine();
            //Delay.msDelay(interval);
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        nxtLogger.stopLogging();
    }

    public void SendLog() {
        System.out.println("Connect to PC and press Connect Button");
//		Button.waitForAnyPress();
        try {
            nxtLogger.sendCache(USB.waitForConnection());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
