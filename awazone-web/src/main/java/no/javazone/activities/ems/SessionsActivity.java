package no.javazone.activities.ems;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import no.javazone.activities.ems.model.ConferenceYear;
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

}
