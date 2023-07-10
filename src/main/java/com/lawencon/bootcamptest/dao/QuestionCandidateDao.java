package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.QuestionCandidate;

public interface QuestionCandidateDao {
	List<QuestionCandidate> getByCandidate(Long candidateId) throws SQLException;
	List<QuestionCandidate> getByType(String typeCode) throws SQLException;
	QuestionCandidate insert(QuestionCandidate questionCandidate) throws SQLException;
	QuestionCandidate getById(Long id) throws SQLException;
}
