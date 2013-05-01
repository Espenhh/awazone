package no.javazone.activities.ems.model;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import net.hamnaberg.json.Item;
import net.hamnaberg.json.Link;
import net.hamnaberg.json.util.Optional;
import no.javazone.activities.ems.ItemHelper;

import com.google.common.base.Function;

public class EmsSession {

	private final String id;
	private final String title;
	private final String summary;
	private final String outline;
	private final String body;
	private final String format;
	private final String audience;
	private final String level;
	private final String lang;
	private final String room;
	private final List<String> speakerNames;
	private final Optional<Link> speakerLink;

	public EmsSession(final String id, final String title, final String summary, final String outline, final String body,
			final String format, final String audience, final String level, final String lang, final String room,
			final List<String> speakerNames, final Optional<Link> speakerLink) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.outline = outline;
		this.body = body;
		this.format = format;
		this.audience = audience;
		this.level = level;
		this.lang = lang;
		this.room = room;
		this.speakerNames = speakerNames;
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

	public String getRoom() {
		return room;
	}

	public List<String> getSpeakerNames() {
		return speakerNames;
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
				String id = ItemHelper.generateId(item);

				String title = ItemHelper.getStringValue(item, "title");
				String summary = ItemHelper.getStringValue(item, "summary");
				String outline = ItemHelper.getStringValue(item, "outline");
				String body = ItemHelper.getStringValue(item, "body");
				String format = ItemHelper.getStringValue(item, "format");
				String audience = ItemHelper.getStringValue(item, "audience");
				String level = ItemHelper.getStringValue(item, "level");
				String lang = ItemHelper.getStringValue(item, "lang");

				List<String> speakerNames = extractSpeakerNames(item);

				String room = extractRoom(item);

				Optional<Link> speakerLink = ItemHelper.getLink(item, "speaker collection");

				return new EmsSession(id, title, summary, outline, body, format, audience, level, lang, room, speakerNames, speakerLink);
			}

			private String extractRoom(final Item item) {
				Optional<Link> roomLink = ItemHelper.getLink(item, "room item");
				if (roomLink.isNone()) {
					return "";
				}
				Optional<String> roomPrompt = roomLink.get().getPrompt();
				if (roomPrompt.isNone()) {
					return "";
				}
				return roomPrompt.get();
			}

			private List<String> extractSpeakerNames(final Item item) {
				List<Link> speakerLinks = ItemHelper.getLinks(item, "speaker item");
				return newArrayList(transform(speakerLinks, new Function<Link, String>() {
					@Override
					public String apply(final Link link) {
						Optional<String> prompt = link.getPrompt();
						if (prompt.isSome()) {
							return prompt.get();
						} else {
							return "";
						}
					}
				}));
			}

		};
	}
}
