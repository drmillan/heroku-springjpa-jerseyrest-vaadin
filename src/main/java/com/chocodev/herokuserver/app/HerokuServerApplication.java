package com.chocodev.herokuserver.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chocodev.herokuserver.logic.TestLogic;


@Component
public class HerokuServerApplication extends ContextAwareApplication {
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
