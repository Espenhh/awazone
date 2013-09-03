package no.javazone.activities.feedback;

import java.net.UnknownHostException;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import no.javazone.representations.feedback.Feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class FeedbackService {

	private static final Logger LOG = LoggerFactory.getLogger(FeedbackService.class);

	public static FeedbackService instance;
	private DBCollection collection;

	public FeedbackService() {
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("javazone");
			collection = db.getCollection("feedback");
		} catch (UnknownHostException e) {
			LOG.warn("Kunne ikke starte MongoDB-klient!", e);
		}
	}

	public List<Feedback> getFeedbacksForTalk(final String talkId) {
		BasicDBObject query = new BasicDBObject("talkId", talkId);

		DBCursor cursor = collection.find(query);

		try {
			System.out.println("Fant " + cursor.size() + " feedbacks som matcher talkid " + talkId);
			return Feedback.convertFromMongo(cursor.toArray());
		} finally {
			cursor.close();
		}
	}

	public void addFeedbackForTalk(final String talkId, final Feedback feedback) {
		if (!feedback.validate()) {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity("Ikke gyldig feedback!").build());
		}

		collection.insert(feedback.toMongoObject(talkId));
	}

	public static FeedbackService getInstance() {
		if (instance == null) {
			instance = new FeedbackService();
		}
		return instance;
	}

}
