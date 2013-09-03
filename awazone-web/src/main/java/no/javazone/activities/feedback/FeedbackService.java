package no.javazone.activities.feedback;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import no.javazone.representations.feedback.Feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.GroupCommand;
import com.mongodb.MongoClient;

public class FeedbackService {

	private static final Logger LOG = LoggerFactory
			.getLogger(FeedbackService.class);

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
		DBCursor cursor = collection.find(new BasicDBObject("talkId", talkId));

		try {
			System.out.println("Fant " + cursor.size()
					+ " feedbacks som matcher talkid " + talkId);
			return Feedback.convertFromMongo(cursor.toArray());
		} finally {
			cursor.close();
		}
	}
	
	public String getSummaryForTalk(final String talkId) {
		// db.feedback.aggregate( {"$match": {"talkId": "123"}}, 
		// 						  { "$group": { _id: "$talkId", number: { $sum:1}, average: { $avg: "$rating"}}})
		
		DBObject match = new BasicDBObject("$match", new BasicDBObject("talkId", talkId));

		DBObject groupFields = new BasicDBObject( "_id", "$talkId");
		groupFields.put("number_of_feedbacks", new BasicDBObject( "$sum", 1));
		groupFields.put("average_rating", new BasicDBObject( "$avg", "$rating"));
		DBObject group = new BasicDBObject("$group", groupFields);

		AggregationOutput output = collection.aggregate(match, group);

		Iterable<DBObject> results = output.results();
		System.out.println(results);
		
		return results.toString();
	}

	public Map<String, List<Feedback>> getAllFeedbacks() {
		List<String> talkIds = collection.distinct("talkId");
		Map<String, List<Feedback>> res = new HashMap<String, List<Feedback>>();
		for (String talkId : talkIds) {
			res.put(talkId, getFeedbacksForTalk(talkId));
		}
		return res;
	}

	public void addFeedbackForTalk(final String talkId, final Feedback feedback) {
		if (!feedback.validate()) {
			throw new WebApplicationException(Response
					.status(Status.BAD_REQUEST).entity("Ikke gyldig feedback!")
					.build());
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