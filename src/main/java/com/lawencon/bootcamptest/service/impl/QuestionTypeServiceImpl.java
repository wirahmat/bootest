package com.lawencon.bootcamptest.service.impl;

import java.sql.SQLException;
import java.util.List;


import com.lawencon.bootcamptest.dao.QuestionTypeDao;
import com.lawencon.bootcamptest.model.QuestionType;
import com.lawencon.bootcamptest.service.QuestionTypeService;

public class QuestionTypeServiceImpl implements QuestionTypeService {
	private final QuestionTypeDao questionTypeDao;
	
	
	public QuestionTypeServiceImpl(QuestionTypeDao questionTypeDao) throws SQLException {
		this.questionTypeDao = questionTypeDao;
	}
	
	@Override
	public List<QuestionType> getAllTypes() throws SQLException {
		final List<QuestionType> questionTypes = questionTypeDao.getAll();
		return questionTypes;
	}
}
