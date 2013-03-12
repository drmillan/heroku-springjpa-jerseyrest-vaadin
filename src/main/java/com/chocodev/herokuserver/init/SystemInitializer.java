package com.chocodev.herokuserver.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.chocodev.herokuserver.logic.TestLogic;


/**
 * 
 *
 */
@Component
public class SystemInitializer implements ServletContextListener{

	@Autowired
	TestLogic testLogic;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		// Forzamos el autowire de poiLogic (Autowire no funciona en el contextListener)
		WebApplicationContextUtils
         .getRequiredWebApplicationContext(event.getServletContext())
         .getAutowireCapableBeanFactory()
         .autowireBean(this);
	}

}
