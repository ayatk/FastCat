# lego-nxt

LEGO Mindstorms NXTをMacでごにょったあとの残骸になる予定のもの

Gradleの中身は https://github.com/j-selby/Rescue-2016 のパクリスペクト

## 使い方

`build`ディレクトリ下にnxjファイルを生成する

    ./gradlew link
    
USBもしくはBluetoothで接続したロボに対してnxjファイルをアップロードする

    ./gradlew upload
    
アップロードして実行を1コマンドでぺいっってするコマンド

    ./gradlew uploadAndRun
