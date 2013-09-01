package no.javazone.representations.feedback;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Feedback {

	@JsonProperty
	public final String feedbackId;
	@JsonProperty
	public final String talkId;
	@JsonProperty
	public final Integer rating;
	@JsonProperty
	public final String comment;

	@JsonCreator
	public Feedback(@JsonProperty("rating") final Integer rating, @JsonProperty("comment") final String comment) {
		feedbackId = null;
		talkId = null;
		this.rating = rating;
		this.comment = comment;
	}

	public Feedback(final String feedbackId, final String talkId, final Integer rating, final String comment) {
		this.feedbackId = feedbackId;
		this.talkId = talkId;
		this.rating = rating;
		this.comment = comment;
	}

}
