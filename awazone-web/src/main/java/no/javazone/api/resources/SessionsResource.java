package no.javazone.api.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import no.javazone.activities.ems.EmsService;
import no.javazone.activities.ems.SessionsActivity;
import no.javazone.api.filters.NoCacheResponseFilter;
import no.javazone.representations.sessions.SimpleSession;

import org.joda.time.DateTime;

import com.sun.jersey.spi.container.ResourceFilters;

@Path("/sessions")
@ResourceFilters(NoCacheResponseFilter.class)
public class SessionsResource {

	private final EmsService emsService = EmsService.getInstance();
	private final SessionsActivity sessionsActivity = SessionsActivity.getInstance();

	@GET
	@Produces(APPLICATION_JSON)
	public Response getSimpleSessions() {
		List<SimpleSession> simpleSessions = sessionsActivity.getSimpleSessions();
		return Response.ok(simpleSessions).build();
	}

	@GET
	@Path("/refresh")
	@Produces(TEXT_PLAIN)
	public String refresh() {
		long bruktTid = emsService.refresh();
		return String.format("%s: Sessions ble oppdatert fra EMS. Tok %s millisekunder\n", new DateTime().toString(), bruktTid);
	}

}