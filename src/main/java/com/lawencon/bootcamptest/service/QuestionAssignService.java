package com.lawencon.bootcamptest.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.QuestionCandidate;

public interface QuestionAssignService {
	QuestionCandidate setQuestion(Long candidateId, List<Long> questionId, Long hrId, Long candidateAssignId) throws SQLException;
	List<QuestionCandidate> getByType(String typeCode) throws SQLException;
}
