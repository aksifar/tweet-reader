package com.gamesys.twitterreader.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Could not find tweet with id.")
public class TweetNotfoundException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;

    public TweetNotfoundException(String message)
    {
        super(message);
    }

}
