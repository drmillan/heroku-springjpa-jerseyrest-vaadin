package com.chocodev.herokuserver.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocodev.herokuserver.model.dto.TestDTO;

/**
 * TestDAO (Repository) for TestDTO objects
 * @author Daniel Rodriguez Millan drm@chocodev.com
 *
 */
@Repository("testDAO")
public interface TestDAO extends JpaRepository<TestDTO, Integer> {
}
