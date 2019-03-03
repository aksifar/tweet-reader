package com.gamesys.twitterreader.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

import com.gamesys.twitterreader.model.Tweet;
import com.gamesys.twitterreader.repository.TweetRepository;

import twitter4j.Status;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Configuration
public class TwitterConfig {

  @Autowired TweetRepository tweetRepository;

  @Value("${app.filter.one}") private String filterOne; 
  @Value("${app.filter.two}") private String filterTwo;
  
  @Bean
  TwitterStreamFactory twitterStreamFactory() {
    return new TwitterStreamFactory();
  }

  @Bean
  TwitterStream twitterStream(TwitterStreamFactory twitterStreamFactory) {
    return twitterStreamFactory.getInstance();
  }

  @Bean
  MessageChannel outputChannel() {
    return MessageChannels.direct().get();
  }

  @Bean
  TwitterMessageProducer twitterMessageProducer(
      TwitterStream twitterStream, MessageChannel outputChannel) {

    TwitterMessageProducer twitterMessageProducer =
        new TwitterMessageProducer(twitterStream, outputChannel);

    twitterMessageProducer.setTerms(Arrays.asList(filterOne, filterTwo));

    return twitterMessageProducer;
  }

  @Bean
  IntegrationFlow twitterFlow(MessageChannel outputChannel) {
	  
	 return  IntegrationFlows.from(outputChannel)
      .transform(Status::getText)
      .handle(message -> {
    		Tweet tweet = new Tweet();
          	tweet.setMessage("Transformation Function: " + message.getPayload().toString());
          	tweetRepository.save(tweet);
      
      }).get();
  }
}