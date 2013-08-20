package no.javazone.api.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import no.javazone.activities.ems.EmsService;
import no.javazone.activities.ems.SessionsActivity;
import no.javazone.activities.feedback.FeedbackService;
import no.javazone.api.filters.NoCacheResponseFilter;
import no.javazone.representations.feedback.Feedback;
import no.javazone.representations.sessions.Session;
import no.javazone.representations.sessions.SimpleSession;

import org.joda.time.DateTime;

import com.sun.jersey.spi.container.ResourceFilters;

@Path("/feedback")
@ResourceFilters(NoCacheResponseFilter.class)
public class FeedbackResource {

	@GET
	@Path("/")
	@Produces(APPLICATION_JSON)
	public Response getAll(@PathParam("id") final String id) {
		Map<String, List<Feedback>> summaries = FeedbackService.getInstance().getAllFeedbacks();
		return Response.ok(summaries).build();
	}
	
	@POST
	@Path("/")
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public Response postNewFeedback(Feedback feedback) {
		System.out.println(feedback);
		return Response.ok(feedback).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(APPLICATION_JSON)
	public Response getFeedbackFor(@PathParam("id") final String id) {
		Feedback testFeedback = new Feedback(id, 4, null);
		return Response.ok(testFeedback).build();
	}

}
