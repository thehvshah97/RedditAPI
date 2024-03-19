package com.Reddit.RedditAPI;

import org.springframework.web.bind.annotation.GetMapping;

public class RedditAccount {
	@GetMapping("/")
	String getAccountDetails(){
		return "";
	}
}
