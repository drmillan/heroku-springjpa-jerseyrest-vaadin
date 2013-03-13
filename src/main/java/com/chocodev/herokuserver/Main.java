package com.chocodev.herokuserver;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

/**
 * Heroku jersey initializer
 * @author Daniel Rodriguez Millan drm@chocodev.com
 *
 */
public class Main {
	public final static String ENV_PORT="PORT";
	public final static int DEFAULT_PORT=5000;
	public static void main(String[] args) throws IOException {
		
		int port = DEFAULT_PORT;
		String envPortString=System.getenv(ENV_PORT);
		if (envPortString!= null) {
			port = Integer.valueOf(System.getenv(envPortString));
		}
		Server server = new Server(port);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		ServletHolder h = new ServletHolder(new ServletContainer());
		h.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
		// Rest services package
		h.setInitParameter("com.sun.jersey.config.property.packages", ServerDefaults.REST_PACKAGE);
		context.addServlet(h, "/*");
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}