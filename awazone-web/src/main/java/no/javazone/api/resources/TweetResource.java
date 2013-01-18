package no.javazone.api.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import no.javazone.activities.TweetService;
import no.javazone.representations.TwitterList;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/tweets")
public class TweetResource {

	private static final Logger LOG = LoggerFactory.getLogger(TweetResource.class);

	private final TweetService tweetService = TweetService.getInstance();

	@GET
	@Produces(APPLICATION_JSON)
	public Response getTweet(@QueryParam("search") final String searchQuery) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		String søkeord = StringUtils.isBlank(searchQuery) ? "javazone" : searchQuery;
		TwitterList tweets = tweetService.søkMedCache(søkeord);

		stopWatch.stop();

		LOG.info("Søket på " + søkeord + " tok " + stopWatch.getTime() + "ms");
		return Response.ok(tweets).build();

	}

}
