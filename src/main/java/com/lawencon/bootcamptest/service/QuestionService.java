package com.lawencon.bootcamptest.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.Question;

public interface QuestionService {
	List<Question>getAll() throws SQLException;
	List<Question>getAllByType(Long questionTypeId) throws SQLException;
	List<Question>getByCandidate(Long candidateId) throws SQLException;	
	List<Question>getByReviewer(Long reviewerId) throws SQLException;
	boolean insert(List<Question> questions) throws SQLException;
}
