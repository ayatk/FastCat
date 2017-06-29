package nxt.cat;

/**
 * 並列形式 PIDコントローラ
 */
public class PID {

    private int target;

    // 操作量の上限値(angle/sec)
    // デフォルトはなんとなく900
    private int max = 900;
    private int min = -max;


    // 比例ゲイン
    private float Kp = 1.0F;
    // 積分ゲイン
    private float Ki = 0.0F;
    // 微分ゲイン
    private float Kd = 0.0F;
    // ローパスフィルターゲイン
    private float N = 1.0F;

    private long cycleTime = 0L;
    private float integral;
    private float dt = 0;
    private float previous_source = 0;

    public PID(int target) {
        this.target = target;
    }

    public PID(int target, float kp, float ki, float kd) {
        this.target = target;
        this.Kp = kp;
        this.Ki = ki;
        this.Kd = kd;
    }

    /**
     * PID制御で目標とする値の設定
     *
     * @param target 目標値
     */
    public void setTarget(int target) {
        this.target = target;
    }

    /**
     * 各ゲイン値の設定
     *
     * @param kp 比例ゲイン
     * @param ki 積分ゲイン
     * @param kd 微分ゲイン
     */
    public void setParameter(float kp, float ki, float kd) {
        this.Kp = kp;
        this.Ki = ki;
        this.Kd = kd;
    }

    /**
     * 出力飽和制限の上限値を設定
     *
     * @param max 設定する上限値
     */
    public void setLimit(int max) {
        this.max = Math.abs(max);
    }

    /**
     * PIDの計算を行い操作量を導出する
     *
     * @param source 各種センサーの入力値
     * @return 出力飽和制限の適用された操作量
     */
    public float exec(int source) {

        // 時間が0の場合は0を返す
        if (cycleTime == 0L) {
            cycleTime = System.currentTimeMillis();
            return 0;
        }

        cycleTime = System.currentTimeMillis();

        int diff = target - source;
        float p = Kp * diff;

        integral += p * dt;
        float i = Ki * integral;

        // TODO: 2017/06/28 ローパスフィルターを実装したい
        float derivative = (source - previous_source) / dt;
        float d = Kd * derivative;

        previous_source = source;

        dt = System.currentTimeMillis() - this.cycleTime;
        cycleTime = System.currentTimeMillis();

        // 普通に並列形式のPID制御を実装したけど
        // 理想PIDとどっちがいいんですかね。
        return mathLimit(p + i + d);
    }

    /**
     * 出力飽和制限
     *
     * @param data 出力飽和制限の適用前の操作量
     * @return 出力飽和制限の適用された操作量
     */
    private float mathLimit(float data) {
        if (data <= min) {
            return min;
        } else if (data >= max) {
            return max;
        } else {
            return data;
        }
    }
}
