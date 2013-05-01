package no.javazone.activities.ems.model;

import net.hamnaberg.json.Item;
import no.javazone.activities.ems.ItemHelper;

import com.google.common.base.Function;

public class EmsSpeaker {

	private final String name;
	private final String bio;

	public EmsSpeaker(final String name, final String bio) {
		this.name = name;
		this.bio = bio;
	}

	public String getName() {
		return name;
	}

	public String getBio() {
		return bio;
	}

	public static Function<Item, EmsSpeaker> collectionItemToSpeaker() {
		return new Function<Item, EmsSpeaker>() {

			@Override
			public EmsSpeaker apply(final Item item) {
				String name = ItemHelper.getStringValue(item, "name");
				String bio = ItemHelper.getStringValue(item, "bio");
				return new EmsSpeaker(name, bio);
			}
		};
	}

}
