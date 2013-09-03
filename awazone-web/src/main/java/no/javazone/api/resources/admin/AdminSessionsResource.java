package no.javazone.api.resources.admin;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import no.javazone.activities.ems.SessionsActivity;
import no.javazone.api.filters.NoCacheResponseFilter;
import no.javazone.representations.sessions.AdminSimpleSession;

import com.sun.jersey.spi.container.ResourceFilters;

@Path("/admin/sessions")
@ResourceFilters(NoCacheResponseFilter.class)
public class AdminSessionsResource {

	private final SessionsActivity sessionsActivity = SessionsActivity.getInstance();

	@GET
	@Produces(APPLICATION_JSON)
	public Response getSimpleAdminSessions() {
		List<AdminSimpleSession> simpleSessions = sessionsActivity.getSimpleSessionsAdmin();
		return Response.ok(simpleSessions).build();
	}

}
