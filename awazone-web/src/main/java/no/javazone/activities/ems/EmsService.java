package no.javazone.activities.ems;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.hamnaberg.json.Collection;
import net.hamnaberg.json.parser.CollectionParser;
import no.javazone.activities.ems.model.ConferenceYear;
import no.javazone.activities.ems.model.EmsSession;

import org.apache.commons.lang3.time.StopWatch;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class EmsService {

	private static final Logger LOG = LoggerFactory.getLogger(EmsService.class);

	// TODO: start fra rotressurs for å finne linken... Ikke hardkod ;)
	private static final String SESSION_LINK_2012 = "http://www.javazone.no/ems/server/events/4c18f45a-054a-4699-a2bc-6a59a9dd8382/sessions";

	private static EmsService instance;

	private final Client jerseyClient;

	private ConferenceYear conferenceYear2012 = null;

	private EmsService() {
		ClientConfig config = new DefaultClientConfig();
		jerseyClient = Client.create(config);
	}

	public long refresh() {
		try {
			LOG.info("Starter innlasting av sessions for 2013 fra EMS");
			StopWatch s = new StopWatch();
			s.start();

			InputStream stream = jerseyClient.resource(SESSION_LINK_2012).get(InputStream.class);
			Collection collection = new CollectionParser().parse(stream);

			ArrayList<EmsSession> sessions = newArrayList(transform(collection.getItems(), EmsSession.collectionToSessions()));
			conferenceYear2012 = new ConferenceYear(sessions, new DateTime());

			long bruktTid = s.getTime();
			LOG.info("Lastet session for 2013 fra EMS på {} ms.", bruktTid);
			return bruktTid;

		} catch (IOException e) {
			LOG.warn("Kunne ikke refreshe sessions fra EMS – fikk feil...", e);
			Response response = Response
					.status(Status.SERVICE_UNAVAILABLE)
					.entity(String.format("%s: Refresh av sessions fra EMS feilet\n", new DateTime().toString()))
					.build();
			throw new WebApplicationException(response);
		}
	}

	public ConferenceYear getConferenceYear() {
		if (conferenceYear2012 == null) {
			throw new WebApplicationException(Status.SERVICE_UNAVAILABLE);
		}
		return conferenceYear2012;
	}

	public static EmsService getInstance() {
		if (instance == null) {
			instance = new EmsService();
		}
		return instance;
	}
}
