package no.javazone.representations.feedback;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import com.google.common.base.Function;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Feedback {

	@JsonProperty
	public final Integer rating;
	@JsonProperty
	public final String comment;

	@JsonCreator
	public Feedback(@JsonProperty("rating") final Integer rating, @JsonProperty("comment") final String comment) {
		this.rating = rating;
		this.comment = comment;
	}

	public boolean validate() {
		return rating > 1 && rating < 4;
	}

	public DBObject toMongoObject(final String talkId) {
		return new BasicDBObject().append("talkId", talkId).append("rating", rating).append("comment", comment);
	}

	public static ArrayList<Feedback> convertFromMongo(final List<DBObject> feedbacksFromDb) {
		return newArrayList(transform(feedbacksFromDb, new Function<DBObject, Feedback>() {
			@Override
			public Feedback apply(final DBObject dbObject) {
				Integer rating;
				try {
					rating = Integer.parseInt(dbObject.get("rating").toString());
				} catch (NumberFormatException e) {
					rating = null;
				}

				Object commentObj = dbObject.get("comment");
				String comment = commentObj != null ? commentObj.toString() : null;
				return new Feedback(rating, comment);
			}
		}));
	}

}
