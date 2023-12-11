+ Notification
  + jdk21およびgradl8.5にしたのでマニュアルインストールの手順をそれに合わせて修正しました
  + MessageBoard.javaのファイル名をmessageBoard.javaに変更しましたが、Windows環境ではうまく反映できないので、手でリネームをしてください

+ Install Java Development Kit(openjdk 21)  

wsl や ubuntuなどで手動インストールする場合、  
まずはパッケージリストを最新化し、OSパッチをあて、インストール済みのjdk17もアンインストール  
その後、JDKをダウンロードして、解凍し、opt以下のフォルダに配置する
<pre>
sudo apt update
sudo apt upgrade
sudo apt autoremove openjdk-17-jdk
mkdir ~/work
cd ~/work
wget https://aka.ms/download-jdk/microsoft-jdk-21.0.1-linux-x64.tar.gz
tar -xvf microsoft-jdk-21.0.1-linux-x64.tar.gz
sudo mv ./jdk-21.0.1+12/ /opt/jdk
</pre>
続いて、JAVA_HOMEとPATHの設定をする
<pre>
vi ~/.bash_profile
</pre>

以下を追加
<pre>
  export JAVA_HOME=/opt/jdk
  export PATH=/opt/jdk/bin:/$PATH
</pre>

続いて設定を反映させる
<pre>
source ~/.bash_profile
</pre>

インストール後以下の様にバージョンの確認を行う
<pre>
java --version
</pre>


+ Install gradle 8.5 
<pre>
 wget https://services.gradle.org/distributions/gradle-8.5-bin.zip
 unzip gradle-8.5-bin.zip
</pre>
続いて、optにgradleフォルダを作って配置
<pre>
mkdir /opt/gradle
sudo mv ./gradle-8.5 /opt/gradle/gradle-8.5
</pre>
.bash_profileに環境変数を追加
<pre>
vi ~/.bash_profile
</pre>
以下を追加
<pre>
export GRADLE_HOME=/opt/gradle/gradle-8.5
export PATH=${GRADLE_HOME}/bin:${PATH}
</pre>
設定を反映
<pre>
source ~/.bash_profile
</pre>
最後にバージョンを確認
<pre>
gradle --version
</pre>

+ Implements Spring Security  
こちらを参考に Spring Securityの実装をしました
https://b1san-blog.com/post/spring/spring-sec-start/
application.propertiesに  
user:hoge  
pwd:fuga  
で設定してあります  

+ Implements Model  
  Modell Attribute を介してページ間のデータ(名前、メール、年齢）を受け渡すまで作成

+ Implements H2 JPA with Spring Security and customize Login form  
  + SecurityConfig.java を作成し、ログイン画面のカスタマイズ設定を行った
  + H2-Consoleに関しては、ログインの必要が無い設定も行っている  
  + User.javaだとテーブル作成時に名前がUserであることが問題となったため、  
    UserInfo.javaに改名し、それとともに関連するソースも修正
  + カスタマイズしたログイン画面が動くようにlogin.htmlを修正
  + 初期ページをindex.htmlへ改名し、inputFormへ遷移するボタンを実装

+ Implements Account Authentication with DB Table
  + アカウント認証をテーブルのデータで行うための実装を行いました
    + user1, pass
    + user2, password
  + ログインユーザーをindex.htmlに表示しました

+ 掲示板の実装をしました
   
  
