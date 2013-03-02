package no.javazone.representations.sessions;

import net.hamnaberg.json.Item;
import net.hamnaberg.json.Property;
import net.hamnaberg.json.Value;
import net.hamnaberg.json.util.Optional;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.google.common.base.Function;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {

	@JsonProperty("title")
	private final String title;
	@JsonProperty("summary")
	private final String summary;
	@JsonProperty("outline")
	private final String outline;
	@JsonProperty("body")
	private final String body;
	@JsonProperty("format")
	private final String format;
	@JsonProperty("audience")
	private final String audience;
	@JsonProperty("level")
	private final String level;
	@JsonProperty("lang")
	private final String lang;

	public Session(final String title, final String summary, final String outline, final String body, final String format,
			final String audience, final String level, final String lang) {
		this.title = title;
		this.summary = summary;
		this.outline = outline;
		this.body = body;
		this.format = format;
		this.audience = audience;
		this.level = level;
		this.lang = lang;
	}

	public String getTitle() {
		return title;
	}

	public String getSummary() {
		return summary;
	}

	public String getOutline() {
		return outline;
	}

	public String getBody() {
		return body;
	}

	public String getFormat() {
		return format;
	}

	public String getAudience() {
		return audience;
	}

	public String getLevel() {
		return level;
	}

	public String getLang() {
		return lang;
	}

	@Override
	public String toString() {
		return "Session [title=" + title + "]";
	}

	public static Function<Item, Session> collectionToSessions() {
		return new Function<Item, Session>() {
			@Override
			public Session apply(final Item item) {
				String title = getStringValue(item, "title");
				String summary = getStringValue(item, "summary");
				String outline = getStringValue(item, "outline");
				String body = getStringValue(item, "body");
				String format = getStringValue(item, "format");
				String audience = getStringValue(item, "audience");
				String level = getStringValue(item, "level");
				String lang = getStringValue(item, "lang");

				return new Session(title, summary, outline, body, format, audience, level, lang);
			}

			private String getStringValue(final Item item, final String key) {
				Optional<Property> property = item.propertyByName(key);
				if (property.isNone()) {
					return "";
				}
				Optional<Value> value = property.get().getValue();
				if (value.isNone()) {
					return "";
				}
				return value.get().asString();
			}
		};
	}

}
