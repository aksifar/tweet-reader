package com.gamesys.twitterreader.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gamesys.twitterreader.model.Tweet;

@Repository
public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long>  {

	Page<Tweet> findAll(Pageable pageable);
}
