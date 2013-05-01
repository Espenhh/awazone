package no.javazone.representations.sessions;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import no.javazone.activities.ems.model.EmsSession;
import no.javazone.representations.Link;
import no.javazone.server.PropertiesLoader;

import org.codehaus.jackson.annotate.JsonProperty;

import com.google.common.base.Function;

public class SimpleSession {

	@JsonProperty("title")
	private final String title;
	@JsonProperty("format")
	private final String format;
	@JsonProperty("level")
	private final String level;
	@JsonProperty("lang")
	private final String lang;
	@JsonProperty("speakers")
	private final List<String> speakers;
	@JsonProperty("links")
	private final List<Link> links;

	public SimpleSession(final String title, final String format, final String level, final String lang, final List<String> speakers,
			final List<Link> links) {
		this.title = title;
		this.format = format;
		this.level = level;
		this.lang = lang;
		this.speakers = speakers;
		this.links = links;
	}

	public static Function<EmsSession, SimpleSession> emsSessionToSimpleSession() {
		return new Function<EmsSession, SimpleSession>() {
			@Override
			public SimpleSession apply(final EmsSession emsSession) {
				return new SimpleSession(emsSession.getTitle(), emsSession.getFormat(), emsSession.getLevel(), emsSession.getLang(),
						emsSession.getSpeakerNames(), createLinks(emsSession));
			}

			private List<Link> createLinks(final EmsSession emsSession) {
				Link detailsLink = new Link("details", PropertiesLoader.getProperty("server.proxy") + "/sessions/" + emsSession.getId());
				return newArrayList(detailsLink);
			}
		};
	}

}
