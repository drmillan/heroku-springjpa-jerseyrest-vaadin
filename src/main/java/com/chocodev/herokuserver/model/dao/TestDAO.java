package com.chocodev.herokuserver.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocodev.herokuserver.model.dto.TestDTO;

@Repository("testDAO")
public interface TestDAO extends JpaRepository<TestDTO, Integer> {
}
