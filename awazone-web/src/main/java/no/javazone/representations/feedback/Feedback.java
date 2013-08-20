package no.javazone.representations.feedback;

import org.codehaus.jackson.annotate.JsonProperty;

public class Feedback {

	@JsonProperty
	public final String id;
	@JsonProperty
	public final Integer rating;
	@JsonProperty
	public final String comment;

	public Feedback(String id, Integer rating, String comment) {
		this.id = id;
		this.rating = rating;
		this.comment = comment;
	}
	
}
