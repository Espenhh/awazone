package no.javazone.representations.twitter;

import org.codehaus.jackson.annotate.JsonProperty;

public class Tweet {

	@JsonProperty
	private final String user;
	@JsonProperty
	private final String tweet;

	public Tweet(final String user, final String tweet) {
		this.user = user;
		this.tweet = tweet;
	}

	public String getUser() {
		return user;
	}

	public String getTweet() {
		return tweet;
	}

}
