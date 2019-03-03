package com.gamesys.twitterreader.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweet implements Serializable{


	private static final long serialVersionUID = -2353350364300681827L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String message;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();
}
