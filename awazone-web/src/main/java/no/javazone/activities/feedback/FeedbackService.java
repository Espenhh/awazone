package no.javazone.activities.feedback;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;

import java.net.UnknownHostException;
import java.util.List;

import no.javazone.representations.feedback.Feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
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
			List<DBObject> feedbacksFromDb = cursor.toArray();

			List<Feedback> feedbacks = newArrayList(transform(feedbacksFromDb, new Function<DBObject, Feedback>() {
				@Override
				public Feedback apply(final DBObject dbObject) {
					return new Feedback(dbObject.get("_id").toString(), dbObject.get("talkId").toString(), Integer.parseInt(dbObject.get(
							"rating").toString()), dbObject.get("comment").toString());
				}
			}));

			return feedbacks;

		} finally {
			cursor.close();
		}
	}

	public void addFeedbackForTalk(final String talkId, final Feedback feedback) {
		BasicDBObject object = new BasicDBObject()
				.append("talkId", talkId)
				.append("rating", feedback.rating)
				.append("comment", feedback.comment);
		collection.insert(object);
		System.out.println("La til objekt. ID=" + object.get("_id"));
	}

	public static FeedbackService getInstance() {
		if (instance == null) {
			instance = new FeedbackService();
		}
		return instance;
	}

}
