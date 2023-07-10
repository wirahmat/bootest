package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.QuestionType;

public interface QuestionTypeDao {
	List<QuestionType> getAll() throws SQLException;
	QuestionType getById(Long typeId) throws SQLException;
}
