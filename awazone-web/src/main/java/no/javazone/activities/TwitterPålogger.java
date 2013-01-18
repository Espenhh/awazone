package no.javazone.activities;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterPålogger {
	public static Twitter hentTwitterObjekt() {
		TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		twitter.setOAuthConsumer(TwitterSecrets.CUSTOMER_KEY, TwitterSecrets.CUSTOMER_SECRET);
		twitter.setOAuthAccessToken(new AccessToken(TwitterSecrets.USER_TOKEN, TwitterSecrets.USER_TOKEN_SECRET));
		return twitter;
	}
}
