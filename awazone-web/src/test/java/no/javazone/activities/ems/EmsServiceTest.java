package no.javazone.activities.ems;

import no.javazone.representations.sessions.ConferenceYear;
import no.javazone.representations.sessions.Session;

import org.junit.Test;

public class EmsServiceTest {

	@Test
	public void skal() {
		EmsService emsService = EmsService.getInstance();
		emsService.refresh();
		ConferenceYear conferenceYear = emsService.getConferenceYear();
		System.out.println("Refreshed: " + conferenceYear.getLastTimeRefreshed());
		for (Session session : conferenceYear.getSessions()) {
			System.out.println(session.toString());
		}
	}

}
