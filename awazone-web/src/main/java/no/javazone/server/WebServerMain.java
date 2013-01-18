package no.javazone.server;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class WebServerMain {
	private static final int WEB_SERVER_PORT = 12345;

	public static void main(final String[] args) throws IOException {
		Server server = new Server(WEB_SERVER_PORT);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
