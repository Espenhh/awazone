package no.javazone.activities.ems;

import no.javazone.activities.ems.model.ConferenceYear;
import no.javazone.activities.ems.model.EmsSession;

import org.junit.Ignore;
import org.junit.Test;

public class EmsServiceTest {

	@Test
	@Ignore
	public void skalHenteDataFraEms() {
		EmsService emsService = EmsService.getInstance();
		emsService.refresh();
		ConferenceYear conferenceYear = emsService.getConferenceYear();
		System.out.println("Refreshed: " + conferenceYear.getLastTimeRefreshed());
		for (EmsSession session : conferenceYear.getSessions()) {
			System.out.println(session.toString());
		}
	}

}
