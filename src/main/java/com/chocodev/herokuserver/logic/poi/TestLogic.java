package com.chocodev.herokuserver.logic.poi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chocodev.herokuserver.model.dao.TestDAO;
import com.chocodev.herokuserver.model.dto.TestDTO;

public class TestLogic {
	@Autowired
	private TestDAO testDAO;
	
	public List<TestDTO> getAll()
	{
		return testDAO.findAll();
	}
}
