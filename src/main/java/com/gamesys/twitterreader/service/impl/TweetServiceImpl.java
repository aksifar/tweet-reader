package com.gamesys.twitterreader.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gamesys.twitterreader.exception.TweetNotfoundException;
import com.gamesys.twitterreader.model.Tweet;
import com.gamesys.twitterreader.repository.TweetRepository;
import com.gamesys.twitterreader.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired TweetRepository tweetRepository;
	
	@Override
	public Page<Tweet> findAll( Pageable pageRequest ) {
		return tweetRepository.findAll( pageRequest );
	}

	@Override
	public Tweet findOne(long id) throws TweetNotfoundException{
		return findTweetChecked(id);
	}
	
	@Override
	@Transactional
	public void delete(Long id) throws TweetNotfoundException {
		Tweet tweetDO = findTweetChecked(id);
		tweetRepository.delete(tweetDO);
	}
	
	private Tweet findTweetChecked(Long id) throws TweetNotfoundException {
		return tweetRepository.findById(id).orElseThrow(() -> new TweetNotfoundException("Could not find tweet entity with id: " + id));
	}
}
