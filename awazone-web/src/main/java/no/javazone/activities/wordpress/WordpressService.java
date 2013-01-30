package no.javazone.activities.wordpress;

import no.javazone.representations.wordpress.Bloggposter;
import no.javazone.server.PropertiesLoader;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class WordpressService {

	private static final String POSTS_URL = "/posts/";

	private static WordpressService instance;

	private final Client jerseyClient;
	private final String rootUrl;

	private WordpressService() {
		ClientConfig config = new DefaultClientConfig();
		config.getClasses().add(JacksonJsonProvider.class);
		jerseyClient = Client.create(config);
		rootUrl = PropertiesLoader.getProperty("wordpress.rooturl");
	}

	public Bloggposter hentBloggposter() {
		return jerseyClient.resource(rootUrl + POSTS_URL).get(Bloggposter.class);
	}

	public static WordpressService getInstance() {
		if (instance == null) {
			instance = new WordpressService();
		}
		return instance;
	}
}
