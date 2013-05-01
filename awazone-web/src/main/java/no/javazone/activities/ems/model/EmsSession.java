package no.javazone.activities.ems.model;

import net.hamnaberg.json.Item;
import net.hamnaberg.json.Link;
import net.hamnaberg.json.util.Optional;
import no.javazone.activities.ems.ItemHelper;

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
	private final Optional<Link> speakerLink;

	public EmsSession(final String id, final String title, final String summary, final String outline, final String body,
			final String format, final String audience, final String level, final String lang, final Optional<Link> speakerLink) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.outline = outline;
		this.body = body;
		this.format = format;
		this.audience = audience;
		this.level = level;
		this.lang = lang;
		this.speakerLink = speakerLink;
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

	public Optional<Link> getSpeakerLink() {
		return speakerLink;
	}

	@Override
	public String toString() {
		return "Session [title=" + title + "]";
	}

	public static Function<Item, EmsSession> collectionItemToSession() {
		return new Function<Item, EmsSession>() {
			@Override
			public EmsSession apply(final Item item) {
				String title = ItemHelper.getStringValue(item, "title");
				String summary = ItemHelper.getStringValue(item, "summary");
				String outline = ItemHelper.getStringValue(item, "outline");
				String body = ItemHelper.getStringValue(item, "body");
				String format = ItemHelper.getStringValue(item, "format");
				String audience = ItemHelper.getStringValue(item, "audience");
				String level = ItemHelper.getStringValue(item, "level");
				String lang = ItemHelper.getStringValue(item, "lang");

				String id = ItemHelper.generateId(item);

				Optional<Link> speakerLink = ItemHelper.getLink(item, "speaker collection");

				return new EmsSession(id, title, summary, outline, body, format, audience, level, lang, speakerLink);
			}

		};
	}
}
