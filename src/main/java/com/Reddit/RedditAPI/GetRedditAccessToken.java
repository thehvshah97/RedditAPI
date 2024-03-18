package com.Reddit.RedditAPI;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
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


class GetRedditAccessToken {

	static String getRedditAccessToken() {
		Properties prop = new Properties();
        String username, password, clientid, clientsecret, accessTokenurl;

        try (InputStream input = GetRedditAccessToken.class.getClassLoader().getResourceAsStream("application.properties")) {
            prop.load(input);
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            clientid = prop.getProperty("clientid");
            clientsecret = prop.getProperty("clientsecret");
            accessTokenurl = prop.getProperty("accessTokenurl");
        } catch (IOException ex) {
            ex.printStackTrace();
            return "Error loading configuration file";
        }
		
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
