package com.gamesys.twitterreader.config;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import twitter4j.TwitterStream;

/*Although we could also expose it as a regular bean without being created by a FactoryBean, 
 * that wouldn't take care of properly shutting it down.
 */
public class TwitterStreamFactory extends AbstractFactoryBean<TwitterStream> {
	  @Override
	  public Class<?> getObjectType() {
	    return TwitterStream.class;
	  }
	  @Override
	  protected TwitterStream createInstance() {
	    return new twitter4j.TwitterStreamFactory().getInstance();
	  }
	  @Override
	  protected void destroyInstance(TwitterStream twitterStream) {
	    twitterStream.shutdown();
	  }
	} 