package no.javazone.api.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import no.javazone.activities.feedback.FeedbackService;
import no.javazone.api.filters.NoCacheResponseFilter;
import no.javazone.representations.feedback.Feedback;

import com.sun.jersey.spi.container.ResourceFilters;

@Path("/feedback")
@ResourceFilters(NoCacheResponseFilter.class)
public class FeedbackResource {

	private final FeedbackService feedbackService = FeedbackService.getInstance();

	@GET
	@Path("/{talkId}")
	@Produces(APPLICATION_JSON)
	public Response getFeedbacksFor(@PathParam("talkId") final String talkId) {
		List<Feedback> feedbacks = feedbackService.getFeedbacksForTalk(talkId);
		return Response.ok(feedbacks).build();
	}

	@POST
	@Path("/{talkId}")
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public Response postNewFeedback(@PathParam("talkId") final String talkId, final Feedback feedback) {
		feedbackService.addFeedbackForTalk(talkId, feedback);
		return Response.ok().build();
	}
}
