package no.javazone.representations.sessions;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import no.javazone.activities.ems.model.EmsSession;
import no.javazone.activities.ems.model.EmsSpeaker;
import no.javazone.representations.Link;
import no.javazone.server.PropertiesLoader;

import org.codehaus.jackson.annotate.JsonProperty;

public class Session {
	@JsonProperty("title")
	private final String title;
	@JsonProperty("format")
	private final String format;
	@JsonProperty("level")
	private final String level;
	@JsonProperty("lang")
	private final String lang;
	@JsonProperty("audience")
	private final String audience;
	@JsonProperty("room")
	private final String room;
	@JsonProperty("body")
	private final String body;
	@JsonProperty("summary")
	private final String summary;
	@JsonProperty("outline")
	private final String outline;
	@JsonProperty("keywords")
	private final List<String> keywords;
	@JsonProperty("speakers")
	private final List<Speaker> speakers;
	@JsonProperty("links")
	private final List<Link> links;

	public Session(final String title, final String format, final String level, final String lang, final String audience,
			final String room, final String body, final String summary, final String outline, final List<String> keywords,
			final List<Speaker> speakers, final List<Link> links) {
		this.title = title;
		this.format = format;
		this.level = level;
		this.lang = lang;
		this.audience = audience;
		this.room = room;
		this.body = body;
		this.summary = summary;
		this.outline = outline;
		this.keywords = keywords;
		this.speakers = speakers;
		this.links = links;
	}

	public static Session createSession(final EmsSession emsSession, final List<EmsSpeaker> speakers) {
		return new Session(emsSession.getTitle(), emsSession.getFormat(), emsSession.getLevel(), emsSession.getLang(),
				emsSession.getAudience(), emsSession.getRoom(), emsSession.getBody(), emsSession.getSummary(), emsSession.getOutline(),
				emsSession.getKeywords(), Speaker.createSpeakers(speakers), createLinks(emsSession));
	}

	private static List<Link> createLinks(final EmsSession emsSession) {
		Link detailsLink = new Link("self", PropertiesLoader.getProperty("server.proxy") + "/sessions/" + emsSession.getId());
		return newArrayList(detailsLink);
	}

}
