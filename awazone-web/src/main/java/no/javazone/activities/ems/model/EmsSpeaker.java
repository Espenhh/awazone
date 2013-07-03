package no.javazone.activities.ems.model;

import net.hamnaberg.json.Item;
import no.javazone.activities.ems.ItemHelper;
import no.javazone.utils.GravatarUtil;

import com.google.common.base.Function;

public class EmsSpeaker {

	private final String name;
	private final String bio;
	private final String gravatarUrl;

	public EmsSpeaker(final String name, final String bio, final String gravatarUrl) {
		this.name = name;
		this.bio = bio;
		this.gravatarUrl = gravatarUrl;
	}

	public String getName() {
		return name;
	}

	public String getBio() {
		return bio;
	}

	public String getGravatarUrl() {
		return gravatarUrl;
	}

	public static Function<Item, EmsSpeaker> collectionItemToSpeaker() {
		return new Function<Item, EmsSpeaker>() {

			@Override
			public EmsSpeaker apply(final Item item) {
				String name = ItemHelper.getStringValue(item, "name");
				String bio = ItemHelper.getStringValue(item, "bio");

				String email = ItemHelper.getStringValue(item, "email");
				String gravatarUrl = GravatarUtil.emailToGravatarUrl(email);

				return new EmsSpeaker(name, bio, gravatarUrl);
			}
		};
	}

}
