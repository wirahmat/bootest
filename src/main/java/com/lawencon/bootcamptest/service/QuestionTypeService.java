package com.lawencon.bootcamptest.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.QuestionType;

public interface QuestionTypeService {
	List<QuestionType> getAllTypes() throws SQLException;
}
