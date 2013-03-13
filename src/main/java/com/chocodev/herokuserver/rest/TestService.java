package com.chocodev.herokuserver.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.chocodev.herokuserver.logic.TestLogic;
import com.chocodev.herokuserver.model.dao.TestDAO;
import com.chocodev.herokuserver.model.dto.TestDTO;

/**
 * Test 
 * @author Daniel Rodriguez Millan drm@chocodev.com
 * @see TestLogic
 * @see TestDAO
 * @see TestDTO
 */
@Component
@Path("/test")
@Scope("request")
public class TestService {
	
	@Autowired
	TestLogic testLogic;
	
	/**
	 * Returns all TestDTO objects as JSON 
	 * @return List<TestDTO>
	 */
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TestDTO> getAll()
	{
		return testLogic.getAll();
	}
	
}
