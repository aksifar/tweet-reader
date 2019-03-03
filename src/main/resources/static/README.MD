This is a spring boot application which runs on port 8090 and can be started by running the main method of com.gamesys.twitterreader.TwitterReaderApplication .

The application finds and saves the tweets from twitter which has the mentioned keywords.
As of now we can configure only 2 keywords in application.properties files.

The default value for these keywords are 'car' and 'gamesys'
app.filter.one=car
app.filter.two=gamesys 

So any tweet which mentions 'car' or 'gamesys' in it will be saved by this application.

All the tweets are prefixed by 'Transformation Function: ' as a part of transformation.

Spring-Integration-Core and Twitter4j is being used for streaming and connection with twitter.

Following properties needs to me provided in 'twitter4j.properties' file for a connection with twitter:
oauth.consumerKey=
oauth.consumerSecret=
oauth.accessToken=
oauth.accessTokenSecret=

I have created a dummy twitter account and have used its property in 'twitter4j.properties'.

Following dependencies have been used in this project:
Maven
Lombok
spring-integration-core
twitter4j


H2 db cab be accessed at : http://localhost:8090/h2-console 
change the following to:
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password: empty

REST APIs:

GET <baseurl>:8090/tweets
			OR
    <baseurl>:8090/tweets?page=1&size=20
 		This will return a paginated view of the tweets.
 		Default page size is 10 and page number is 0;
 	
GET <baseurl>:8090/tweets/{tweet-id}
		This return one specific tweet with the mentioned tweet id.
		
DELETE <baseurl>:8090/tweets/{tweet-id}
		This deletes the specific tweet with the mentioned tweet id.
		
		
 		