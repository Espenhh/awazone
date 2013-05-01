package no.javazone.activities.ems.model;

import java.net.URI;
import java.security.MessageDigest;

import net.hamnaberg.json.Item;
import net.hamnaberg.json.Property;
import net.hamnaberg.json.Value;
import net.hamnaberg.json.util.Optional;

import org.apache.commons.codec.binary.Hex;

import com.google.common.base.Function;

public class EmsSession {

	private final String title;
	private final String summary;
	private final String outline;
	private final String body;
	private final String format;
	private final String audience;
	private final String level;
	private final String lang;
	private final String id;

	public EmsSession(final String id, final String title, final String summary, final String outline, final String body,
			final String format, final String audience, final String level, final String lang) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.outline = outline;
		this.body = body;
		this.format = format;
		this.audience = audience;
		this.level = level;
		this.lang = lang;
	}

	public String getId() {
		return id;
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

	public static Function<Item, EmsSession> collectionToSessions(final MessageDigest mda) {
		return new Function<Item, EmsSession>() {
			@Override
			public EmsSession apply(final Item item) {
				String title = getStringValue(item, "title");
				String summary = getStringValue(item, "summary");
				String outline = getStringValue(item, "outline");
				String body = getStringValue(item, "body");
				String format = getStringValue(item, "format");
				String audience = getStringValue(item, "audience");
				String level = getStringValue(item, "level");
				String lang = getStringValue(item, "lang");

				String id = generateId(mda, item);

				return new EmsSession(id, title, summary, outline, body, format, audience, level, lang);
			}

			private String generateId(final MessageDigest mda, final Item item) {
				URI href = item.getHref();
				return new String(Hex.encodeHex(mda.digest(href.toString().getBytes())));
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
