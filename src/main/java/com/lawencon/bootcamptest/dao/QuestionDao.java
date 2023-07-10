package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.Question;

public interface QuestionDao {
	List<Question> getAll() throws SQLException;
	List<Question> getAllByType(Long questionTypeId) throws SQLException;
	List<Question> getByIdCandidate(Long candidateId) throws SQLException;
	List<Question> getByIdReviewer(Long reviewerId) throws SQLException;
	Question insert (Question question) throws SQLException;
	Question getById (Long questionId) throws SQLException;
}
