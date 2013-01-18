package no.javazone.api.errorhandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import no.javazone.representations.Feilmelding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {

	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public Response toResponse(final Throwable exception) {
		LOG.error("FEIL!", exception);
		return Response.serverError().entity(new Feilmelding("Oh, snap! 500 FTW!")).build();
	}

}
