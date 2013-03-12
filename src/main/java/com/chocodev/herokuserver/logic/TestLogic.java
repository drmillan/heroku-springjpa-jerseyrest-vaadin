package com.chocodev.herokuserver.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chocodev.herokuserver.model.dao.TestDAO;
import com.chocodev.herokuserver.model.dto.TestDTO;

@Component
public class TestLogic {
	@Autowired
	private TestDAO testDAO;
	
	public List<TestDTO> getAll()
	{
		return testDAO.findAll();
	}
}
