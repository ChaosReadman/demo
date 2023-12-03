+ Install Java Development Kit(openjdk 21)  
Download from following url
https://learn.microsoft.com/ja-jp/java/openjdk/download#openjdk-17  
インストール後以下の様にバージョンの確認を行う
<pre>
chaos@xxx:~/java_lecture/sbTest/demo$ java --version
</pre>

+ Install gradle 8.5 
follow the url
https://gradle.org/install/  
インストール後以下の様にバージョンの確認を行う
<pre>
chaos@xxx:~/java_lecture/sbTest/demo$ gradle --version
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
  
