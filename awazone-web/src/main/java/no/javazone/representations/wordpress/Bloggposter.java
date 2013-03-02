package no.javazone.representations.wordpress;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bloggposter {

	@SuppressWarnings("unused")
	@JsonProperty
	private int found;

	@JsonProperty
	private List<Bloggpost> posts;

	public List<Bloggpost> getPosts() {
		return posts;
	}

}
