package com.Reddit.RedditAPI;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

public class RedditAccount {
	
	private static RedditConfig redditConfig = RedditConfig.getInstance();
	
	@GetMapping("/account/me")
	String getAccountDetails(){
		String accessToken = GetRedditAccessToken.getRedditAccessToken();
		String accessTokenType = redditConfig.getAccessTokenType();
		String apiCallEndpoint = redditConfig.getUrl() + "/api/v1/me";
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(apiCallEndpoint);
		
		try {
			httpGet.setHeader("Authorization", accessTokenType + " " + accessToken);
			HttpResponse response;
			try {
				response = httpClient.execute(httpGet);
				String responseString = null;
				try {
					responseString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
					return responseString;
				} catch(IOException e) {
					return "Error in HTTP Response";
				}
			} catch(ClientProtocolException e) {
				return "Error in HTTP Client";
			} catch(IOException e) {
				return "Error in HTTP Response";
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@GetMapping("/account/mySubreddits")
	String getKarmaDetails() {
		String accessToken = GetRedditAccessToken.getRedditAccessToken();
		String accessTokenType = redditConfig.getAccessTokenType();
		String apiCallEndpoint = redditConfig.getUrl() + "/api/v1/me/karma";
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(apiCallEndpoint);
		
		try {
			httpGet.setHeader("Authorization", accessTokenType + " " + accessToken);
			HttpResponse response;
			try {
				response = httpClient.execute(httpGet);
				String responseString = null;
				try {
					responseString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
					return responseString;
				} catch(IOException e) {
					return "Error in HTTP Response";
				}
			} catch(ClientProtocolException e) {
				return "Error in HTTP Client";
			} catch(IOException e) {
				return "Error in HTTP Response";
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@GetMapping("/account/trophies")
	String getTrophies() {
		String accessToken = GetRedditAccessToken.getRedditAccessToken();
		String accessTokenType = redditConfig.getAccessTokenType();
		String apiCallEndpoint = redditConfig.getUrl() + "/api/v1/me/trophies";
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(apiCallEndpoint);
		
		try {
			httpGet.setHeader("Authorization", accessTokenType + " " + accessToken);
			HttpResponse response;
			try {
				response = httpClient.execute(httpGet);
				String responseString = null;
				try {
					responseString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
					return responseString;
				} catch(IOException e) {
					return "Error in HTTP Response";
				}
			} catch(ClientProtocolException e) {
				return "Error in HTTP Client";
			} catch(IOException e) {
				return "Error in HTTP Response";
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@GetMapping("/account/trophies")
	String getPrefrences() {
		String accessToken = GetRedditAccessToken.getRedditAccessToken();
		String accessTokenType = redditConfig.getAccessTokenType();
		String apiCallEndpoint = redditConfig.getUrl() + "/api/v1/me/prefs";
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(apiCallEndpoint);
		
		try {
			httpGet.setHeader("Authorization", accessTokenType + " " + accessToken);
			HttpResponse response;
			try {
				response = httpClient.execute(httpGet);
				String responseString = null;
				try {
					responseString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
					return responseString;
				} catch(IOException e) {
					return "Error in HTTP Response";
				}
			} catch(ClientProtocolException e) {
				return "Error in HTTP Client";
			} catch(IOException e) {
				return "Error in HTTP Response";
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@PatchMapping("/editprefrences")
	String editPrefences() {
		return "";
	}
}
