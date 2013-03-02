package no.javazone.api.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import no.javazone.activities.ems.EmsService;
import no.javazone.api.filters.NoCacheResponseFilter;
import no.javazone.representations.sessions.ConferenceYear;

import org.joda.time.DateTime;

import com.sun.jersey.spi.container.ResourceFilters;

@Path("/sessions")
@ResourceFilters(NoCacheResponseFilter.class)
public class SessionsResource {

	private final EmsService emsService = EmsService.getInstance();

	@GET
	@Produces(APPLICATION_JSON)
	public Response getConferenceYear() {
		ConferenceYear conferenceYear = emsService.getConferenceYear();
		return Response.ok(conferenceYear).build();
	}

	@GET
	@Path("/refresh")
	@Produces(TEXT_PLAIN)
	public String refresh() {
		long bruktTid = emsService.refresh();
		return String.format("%s: Sessions ble oppdatert fra EMS. Tok %s millisekunder", new DateTime().toString(), bruktTid);
	}

}
