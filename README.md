+ Install Java Development Kit(openjdk 17.0.8.1)
  Download from following url
  https://learn.microsoft.com/ja-jp/java/openjdk/download#openjdk-17

<pre>
chaos@xxx:~/java_lecture/sbTest/demo$ java --version
openjdk 17.0.8.1 2023-08-24
OpenJDK Runtime Environment (build 17.0.8.1+1-Ubuntu-0ubuntu120.04)
OpenJDK 64-Bit Server VM (build 17.0.8.1+1-Ubuntu-0ubuntu120.04, mixed mode, sharing)
chaos@xxx:~/java_lecture/sbTest/demo$ gradle --version
</pre>

+ Install gradle 8.3
  follow the url
  https://gradle.org/install/
  
<pre>
------------------------------------------------------------
Gradle 8.3
------------------------------------------------------------

Build time:   2023-08-17 07:06:47 UTC
Revision:     8afbf24b469158b714b36e84c6f4d4976c86fcd5

Kotlin:       1.9.0
Groovy:       3.0.17
Ant:          Apache Ant(TM) version 1.10.13 compiled on January 4 2023
JVM:          17.0.8.1 (Private Build 17.0.8.1+1-Ubuntu-0ubuntu120.04)
OS:           Linux 5.15.90.1-microsoft-standard-WSL2+ amd64


</pre>

+ Implements Spring Security
  
こちらを参考に Spring Securityの実装をしました
https://b1san-blog.com/post/spring/spring-sec-start/
application.propertiesに  
user:hoge  
pwd:fuga  
で設定してあります  

+ Implements Model
