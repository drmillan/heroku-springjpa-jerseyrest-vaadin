package com.chocodev.herokuserver.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chocodev.herokuserver.logic.TestLogic;


/**
 * Vaadin Application entry point
 * @author Daniel Rodriguez Millan drm@chocodev.com
 *
 */
@Component
public class HerokuServerApplication extends ContextAwareApplication {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	TestLogic testLogic;
	
	
	@Override
	protected void initApplication() {
		super.initApplication();
	}

	public TestLogic getTestLogic() {
		return testLogic;
	}
}
