package no.javazone.server;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class WebServerMain {

	private static final Logger LOG = LoggerFactory.getLogger(WebServerMain.class);

	public static void main(final String[] args) throws IOException {
		PropertiesLoader.initialize();
		int port = Integer.parseInt(PropertiesLoader.getProperty("server.port"));
		Server server = new Server(port);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		ServletHolder h = new ServletHolder(new ServletContainer());
		h.setInitParameter("com.sun.jersey.config.property.packages", "no.javazone.api");
		h.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
		context.addServlet(h, "/*");
		try {
			server.start();
			server.join();
			LOG.info("Server startet på port " + port);
		} catch (Exception e) {
			LOG.error("Kunne ikke starte server!", e);
		}
	}
}
