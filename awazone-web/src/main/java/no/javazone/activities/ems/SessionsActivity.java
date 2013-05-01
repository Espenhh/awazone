package no.javazone.activities.ems;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import no.javazone.activities.ems.model.ConferenceYear;
import no.javazone.activities.ems.model.EmsSession;
import no.javazone.representations.sessions.Session;
import no.javazone.representations.sessions.SimpleSession;

public class SessionsActivity {

	private static SessionsActivity instance;

	private final EmsService emsService = EmsService.getInstance();

	private SessionsActivity() {
	}

	public List<SimpleSession> getSimpleSessions() {
		ConferenceYear conferenceYear = emsService.getConferenceYear();
		return newArrayList(transform(conferenceYear.getSessions(), SimpleSession.emsSessionToSimpleSession()));
	}

	public static SessionsActivity getInstance() {
		if (instance == null) {
			instance = new SessionsActivity();
		}
		return instance;
	}

	public Session getSession(final String id) {
		EmsSession emsSession = emsService.getSession(id);
		if (emsSession == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return Session.emsSessionToSession().apply(emsSession);
	}

}
