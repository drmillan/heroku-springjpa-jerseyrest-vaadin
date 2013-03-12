package com.chocodev.herokuserver;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

/**
 * Heroku jersey initializer
 * @author drmillan
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {

		int port = 5000;
		if (System.getenv("PORT") != null) {
			port = Integer.valueOf(System.getenv("PORT"));
		}
		Server server = new Server(port);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		ServletHolder h = new ServletHolder(new ServletContainer());
		h.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
		// Rest services package
		h.setInitParameter("com.sun.jersey.config.property.packages", "com.chocodev.herokuserver.rest");
		context.addServlet(h, "/*");
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}