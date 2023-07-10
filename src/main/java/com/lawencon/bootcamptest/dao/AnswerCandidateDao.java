package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.AnswerCandidate;

public interface AnswerCandidateDao {
	AnswerCandidate insert(AnswerCandidate answerCandidate) throws SQLException;
	List<AnswerCandidate> getByType(String typeCode) throws SQLException;
	AnswerCandidate getById(Long id) throws SQLException;
}
