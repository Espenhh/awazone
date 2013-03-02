package no.javazone.representations.sessions;

import no.javazone.activities.ems.model.EmsSession;

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

	public SimpleSession(final String title, final String format, final String level, final String lang) {
		this.title = title;
		this.format = format;
		this.level = level;
		this.lang = lang;
	}

	public static Function<EmsSession, SimpleSession> emsSessionToSimpleSession() {
		return new Function<EmsSession, SimpleSession>() {
			@Override
			public SimpleSession apply(final EmsSession emsSession) {
				return new SimpleSession(emsSession.getTitle(), emsSession.getFormat(), emsSession.getLevel(), emsSession.getLang());
			}
		};
	}

}
