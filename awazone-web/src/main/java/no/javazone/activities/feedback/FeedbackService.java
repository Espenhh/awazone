package no.javazone.activities.feedback;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.javazone.representations.feedback.Feedback;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class FeedbackService {

	public static FeedbackService instance;
	private DB db;
	
	public FeedbackService() {
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("localhost", 27017);
			db = mongoClient.getDB("feedbackz");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, List<Feedback>> getAllFeedbacks() {
		HashMap<String, List<Feedback>> dummyFeedbacks = new HashMap<String, List<Feedback>>();
		dummyFeedbacks.put("talk-1", Arrays.asList(new Feedback("talk-1", 5, "amazing")));
		dummyFeedbacks.put("talk-2", Arrays.asList(new Feedback("talk-2", 2, "hated it")));
		return dummyFeedbacks;
	}
	
	public static FeedbackService getInstance() {
		if (instance==null) {
			instance = new FeedbackService();
		}
		return instance;
	}

}
