package nxt.libs.addon;

import lejos.nxt.Motor;
import lejos.nxt.comm.USB;
import lejos.robotics.Color;
import lejos.util.LogColumn;
import lejos.util.NXTDataLogger;

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
    private static Logger _logger = new Logger();
    /**
     * ロガーの実体
     */
    NXTDataLogger _nxtLogger = new NXTDataLogger();
    /**
     * スレッドがアクティブならtrue
     * falseにするとスレッドは停止
     */
    private boolean _isActive = true;

    /**
     * ログ記録間隔 (ms)
     * setIntervalメソッドでも設定可能
     */
    private int _interval = 50;

    /**
     * ログの列の設定
     */
    private LogColumn[] _logColumns = {
            new LogColumn("Color", LogColumn.DT_INTEGER),
            new LogColumn("R", LogColumn.DT_INTEGER),
            new LogColumn("G", LogColumn.DT_INTEGER),
            new LogColumn("B", LogColumn.DT_INTEGER),
            new LogColumn("Brightness", LogColumn.DT_INTEGER),
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

    /**
     * インスタンスの取得
     *
     * @return 唯一のインスタンス
     */
    public static Logger getInstance() {
        return _logger;
    }

    /**
     * ログ記録スレッドの実行停止
     */
    public void stopThread() {
        this._isActive = false;
    }


    /**
     * ログ間隔時間の設定
     *
     * @param interval ログ間隔時間(ms)
     */
    public void setInterval(int interval) {
        _interval = interval;
    }

    /**
     * ログ記録スレッドの開始
     */
    @Override
    public void run() {
        _isActive = true;
        // ログ記録開始
        _nxtLogger.startCachingLog();
        // ログの列の設定
        _nxtLogger.setColumns(_logColumns);
        while (_isActive) {
            Color color = checker.getColor();
            _nxtLogger.writeLog(color.getColor());
            _nxtLogger.writeLog(color.getRed());
            _nxtLogger.writeLog(color.getGreen());
            _nxtLogger.writeLog(color.getBlue());
            _nxtLogger.writeLog(Math.max(Math.max(color.getRed(), color.getGreen()), color.getBlue()));
            _nxtLogger.writeLog(Motor.A.getSpeed());
            _nxtLogger.writeLog(Motor.C.getSpeed());
            _nxtLogger.finishLine();
            //Delay.msDelay(_interval);
            try {
                Thread.sleep(_interval);
            } catch (Exception e) {

            }
        }
        _nxtLogger.stopLogging();
    }

    public void SendLog() {
        System.out.println("Connect to PC and press Connect Button");
//		Button.waitForAnyPress();
        try {
            _nxtLogger.sendCache(USB.waitForConnection());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
