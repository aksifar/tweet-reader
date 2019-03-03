package com.gamesys.twitterreader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamesys.twitterreader.exception.TweetNotfoundException;
import com.gamesys.twitterreader.model.Tweet;
import com.gamesys.twitterreader.service.TweetService;

@RestController
@RequestMapping("/tweets")
public class TweetController {

	@Autowired TweetService tweetService;
	
	@GetMapping
	public ResponseEntity<Object> getAllTweets(
			@RequestParam(value = "page", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "size", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value="sortDirection", defaultValue="desc",required=false) String sortDirection,
			@RequestParam(value="sortBy", defaultValue="dateCreated",required=false) String sortColumn){
		
		Sort sort = new Sort(Direction.fromString(sortDirection), sortColumn);
		Pageable pageRequest = PageRequest.of(pageNumber, pageSize, sort);
		
		return new ResponseEntity<Object>(tweetService.findAll(pageRequest), HttpStatus.OK);
	}
	
	@GetMapping("/{tweet-id}")
	public ResponseEntity<Tweet> findOne(@PathVariable("tweet-id") long id) throws TweetNotfoundException{
		return new ResponseEntity<Tweet>(tweetService.findOne(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{tweet-id}")
	public void delete(@PathVariable("tweet-id") long id) throws TweetNotfoundException{
		tweetService.delete(id);
	}
}
