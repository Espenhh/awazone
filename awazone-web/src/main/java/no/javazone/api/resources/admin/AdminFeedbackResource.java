package no.javazone.api.resources.admin;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import no.javazone.activities.feedback.FeedbackService;
import no.javazone.api.filters.NoCacheResponseFilter;
import no.javazone.representations.feedback.Feedback;

import com.sun.jersey.spi.container.ResourceFilters;

@Path("/admin/feedback")
@ResourceFilters(NoCacheResponseFilter.class)
public class AdminFeedbackResource {

	private final FeedbackService feedbackService = FeedbackService.getInstance();

	@GET
	@Produces(APPLICATION_JSON)
	public Response getAllFeedbacks() {
		Map<String, List<Feedback>> feedbacks = feedbackService.getAllFeedbacks();
		return Response.ok(feedbacks).build();
	}

	@GET
	@Path("/{talkId}")
	@Produces(APPLICATION_JSON)
	public Response getFeedbacksFor(@PathParam("talkId") final String talkId) {
		List<Feedback> feedbacks = feedbackService.getFeedbacksForTalk(talkId);
		return Response.ok(feedbacks).build();
	}

	@GET
	@Path("/{talkId}/summary")
	@Produces(APPLICATION_JSON)
	public Response getSummaryFor(@PathParam("talkId") final String talkId) {
		String s = feedbackService.getSummaryForTalk(talkId);
		return Response.ok(s).build();
	}

}
