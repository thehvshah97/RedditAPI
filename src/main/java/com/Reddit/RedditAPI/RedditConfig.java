package com.Reddit.RedditAPI;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class RedditConfig {
	
	private static RedditConfig redditConfig;
	Properties prop = new Properties();
    static String username, password, clientid, clientsecret, accessTokenurl, accessTokenType, url;
	
    private RedditConfig() {
    	try (InputStream input = GetRedditAccessToken.class.getClassLoader().getResourceAsStream("application.properties")) {
            prop.load(input);
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            clientid = prop.getProperty("clientid");
            clientsecret = prop.getProperty("clientsecret");
            accessTokenurl = prop.getProperty("accessTokenurl");
            accessTokenType = prop.getProperty("accessTokenType");
            url = prop.getProperty("url");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static RedditConfig getInstance() {
    	if(redditConfig == null) {
    		redditConfig = new RedditConfig();
    	}
    	return redditConfig;
    }
    
    static String getAccessTokenType() {
		return accessTokenType;
	}
    
    static String getUsername() {
    	return username;
    }
    
    static String getPassword() {
    	return password;
    }

    static String getClientId() {
    	return clientid;
    }
    
    static String getClientSecret() {
    	return clientsecret;
    }
    
    static String getAccessTokenurl() {
    	return accessTokenurl;
    }

    static String getUrl() {
		return url;
	}
}
