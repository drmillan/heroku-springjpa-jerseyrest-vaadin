package com.chocodev.herokuserver.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.chocodev.herokuserver.logic.TestLogic;

@Component
@Path("/test")
@Scope("request")
public class AliveService {
	
	@Autowired
	TestLogic testLogic;
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public String isAlive()
	{
		return testLogic.getAll().toString();
	}
	
}
