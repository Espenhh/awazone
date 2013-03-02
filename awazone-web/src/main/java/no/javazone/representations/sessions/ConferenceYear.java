package no.javazone.representations.sessions;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConferenceYear {

	@JsonProperty("sessions")
	private final List<Session> sessions;

	@JsonProperty("lastTimeRefreshed")
	private final DateTime lastTimeRefreshed;

	public ConferenceYear(final List<Session> sessions, final DateTime lastTimeRefreshed) {
		this.sessions = sessions;
		this.lastTimeRefreshed = lastTimeRefreshed;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public DateTime getLastTimeRefreshed() {
		return lastTimeRefreshed;
	}

}
