package nxt.libs.abst;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public abstract class AbstDriver {

    private float baseSpeed = 400;

    protected static NXTRegulatedMotor rightWheel = Motor.A;
    protected static NXTRegulatedMotor leftWheel = Motor.C;

    /**
     * 左にステアリングを切る
     */
    public abstract void turnLeft();

    /**
     * 右にステアリングを切る
     */
    public abstract void turnRight();

    // 20160920追加

    /**
     * 左にきつくステアリングを切る
     */
    public abstract void turnLeftQuick();

    // 20160920追加

    /**
     * 右にきつくステアリングを切る
     */
    public abstract void turnRightQuick();

    // 20160920追加

    /**
     * 左にゆるくステアリングを切る
     */
    public abstract void turnLeftSlack();

    // 20160920追加

    /**
     * 右にゆるくステアリングを切る
     */
    public abstract void turnRightSlack();

    // 20160509追加

    /**
     * 直進
     */
    public abstract void goStraight();

    // 20160920追加

    /**
     * 速く直進
     */
    public abstract void goStraightFast();

    // 20160920追加

    /**
     * 遅く直進
     */
    public abstract void goStraightSlow();

    /**
     * 回転開始
     */
    public abstract void start();

    /**
     * ホイールを一定角度だけ回転
     *
     * @param angle 回転角度(度)
     */
    public void rotate(int angle) {
        rightWheel.rotate(angle, true);
        leftWheel.rotate(angle);
    }

    /**
     * ホイールを一定角度だけ回転
     *
     * @param rightAngle 右ホイールの回転角度(度)
     * @param leftAngle  左ホイールの回転角度(度)
     */
    public void rotate(int rightAngle, int leftAngle) {
        if (rightAngle < leftAngle) {
            rightWheel.rotate(rightAngle, true);
            leftWheel.rotate(leftAngle);
        } else {
            leftWheel.rotate(leftAngle, true);
            rightWheel.rotate(rightAngle);
        }
    }

    /**
     * 前進
     */
    public void forward() {
        rightWheel.forward();
        leftWheel.forward();
    }

    /**
     * 後進
     */
    public void backward() {
        rightWheel.backward();
        leftWheel.backward();
    }

    /**
     * 停止
     */
    public void stop() {
        rightWheel.stop(true);
        leftWheel.stop();
    }

    public float getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(float baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    /**
     * スピードの設定
     *
     * @param speed 設定するスピード (度/秒)
     */
    public void setSpeed(float speed) {
        rightWheel.setSpeed(speed);
        leftWheel.setSpeed(speed);
    }

    /**
     * 左右のホイールごとにスピードを設定する
     *
     * @param rightSpeed 右ホイールに設定するスピード(度/秒)
     * @param leftSpeed  左ホイールに設定するスピード(度/秒)
     */
    public void setSpeed(float rightSpeed, float leftSpeed) {
        rightWheel.setSpeed(rightSpeed);
        leftWheel.setSpeed(leftSpeed);
    }

    /**
     * スピードを変化させる
     *
     * @param diff スピードの変化量 (度/秒)
     */
    public void changeSpeed(float diff) {
        rightWheel.setSpeed(rightWheel.getSpeed() + diff);
        leftWheel.setSpeed(leftWheel.getSpeed() + diff);
    }

    /**
     * スピードを変化させる
     *
     * @param rightDiff 右ホイールのスピード変化量(度/秒)
     * @param leftDiff  左ホイールのスピード変化量(度/秒)
     */
    public void changeSpeed(float rightDiff, float leftDiff) {
        rightWheel.setSpeed(rightWheel.getSpeed() + rightDiff);
        leftWheel.setSpeed(leftWheel.getSpeed() + leftDiff);
    }

    // 2016.05.06 追加

    /**
     * 右ホイールの回転速度取得
     *
     * @return 右ホイールの回転速度(度/秒)
     */
    public float getSpeedRight() {
        return rightWheel.getSpeed();
    }

    // 2016.05.06 追加

    /**
     * 左ホイールの回転速度取得
     *
     * @return 左ホイールの回転速度(度/秒)
     */
    public int getSpeedLeft() {
        return leftWheel.getSpeed();
    }

}
