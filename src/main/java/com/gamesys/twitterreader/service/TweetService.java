package com.gamesys.twitterreader.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gamesys.twitterreader.exception.TweetNotfoundException;
import com.gamesys.twitterreader.model.Tweet;

@Service
public interface TweetService {

	public Page<Tweet> findAll(Pageable pageRequest);
	public Tweet findOne(long id) throws TweetNotfoundException;
	public void delete(Long id) throws TweetNotfoundException;
}
