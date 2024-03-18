package com.Reddit.RedditAPI;

import com.Reddit.RedditAPI.GetRedditAccessToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class RedditLinksAndComments {
	
	@Autowired
    private RedditConfig redditConfig;
	
	public RedditLinksAndComments(RedditConfig redditConfig) {
        this.redditConfig = redditConfig;
    }
	
	@PostMapping("/submitLink")
	public static String submitLinktoReddit(@RequestParam String[] values) {
	    String accessToken = GetRedditAccessToken.getRedditAccessToken();
		String accessTokenType = "";
		String username = "";
		String subRedditName = "";
		String subRedditDisplayName = "";
		String subRedditPostTitle = "";
		String subredditUrl = "";
		String apiCallEndpoint = "https://oauth.reddit.com/submit";
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(apiCallEndpoint);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(4);
		params.add(new BasicNameValuePair("url", subredditUrl));
		params.add(new BasicNameValuePair("title", subRedditPostTitle));
		params.add(new BasicNameValuePair("sr", subRedditName));
		params.add(new BasicNameValuePair("kind", "Link"));
		
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			httpPost.setHeader("User-Agent", subRedditDisplayName + "(by /u/" + username + ")");
			httpPost.setHeader("Authorization", accessTokenType + " " + accessToken);
			HttpResponse response;
			try {
				response = httpClient.execute(httpPost);
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
}
