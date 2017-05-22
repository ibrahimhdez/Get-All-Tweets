# Get-All-Tweets
Get all the tweets of a twitter account.

Requirements:
* [Create a Twitter Application Manager Account.](https://apps.twitter.com/)
* Change in the code the lines 31, 32, 33 and 34 with the information of your Twitter Application Manager account.
* Download the JAR of the Twitter´s API. You can download [here.](https://mvnrepository.com/artifact/org.twitter4j/twitter4j-core/4.0.4) (Needed the 4.0.4 version or later).

Instrucctions:
* To Compile: javac -cp ".:/path/to/jar/example.jar" GetTweets.java
* To execute: java -cp ".:/path/to/jar/example.jar" GetTweets \<TwitterAccount> \<OutputFile>
