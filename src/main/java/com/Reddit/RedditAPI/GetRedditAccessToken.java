package com.Reddit.RedditAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


class GetRedditAccessToken {
	private static RedditConfig redditConfig = RedditConfig.getInstance();
	static String getRedditAccessToken() {
		String username = redditConfig .getUsername();
		String password = redditConfig.getPassword();
		String clientid = redditConfig.getClientId();
		String clientsecret = redditConfig.getClientSecret();
		String accessTokenurl = redditConfig.getAccessTokenurl();

		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(
			    AuthScope.ANY,
			    new UsernamePasswordCredentials(clientid, clientsecret)
			);
		HttpClient httpclient = HttpClientBuilder
				.create()
				.setDefaultCredentialsProvider(credentialsProvider)
				.build();
		HttpPost httppost = new HttpPost( accessTokenurl);
		List<NameValuePair> params = new ArrayList<NameValuePair>(3);
		params.add(new BasicNameValuePair("grant_type", "password"));
		params.add(new BasicNameValuePair(username, "username"));
		params.add(new BasicNameValuePair(password, "password"));
		
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params));
			httppost.setHeader("User-Agent", "/u/user v1.0");
			HttpResponse response;
			try {
				response = httpclient.execute(httppost);
				String responseString = null;
				try {
					responseString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
					ObjectMapper objectMapper = new ObjectMapper();
					JsonResponse jsonResponse = objectMapper.readValue(responseString, JsonResponse.class);
					String accessToken = jsonResponse.getAccess_token();
					return accessToken;
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


class JsonResponse{
	private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}