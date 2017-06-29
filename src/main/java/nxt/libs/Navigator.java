package nxt.libs;


import nxt.cat.PID;
import nxt.libs.abst.AbstDriver;
import nxt.libs.addon.SensorChecker;

/**
 * Navigatorのための抽象クラス．
 * 具象クラスでdecisionメソッドを実装すること．
 */
public interface Navigator {
    /**
     * 走行戦略を具体化するメソッド
     *
     * @param checker カラーセンサー測定スレッドのクラス
     * @param driver  走行のための具象クラス
     * @param pid     PIDのパラメータを設定した PID クラス
     */
    void decision(SensorChecker checker, AbstDriver driver, PID pid);
}
