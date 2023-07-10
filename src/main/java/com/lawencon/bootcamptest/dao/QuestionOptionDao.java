package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.QuestionOption;

public interface QuestionOptionDao {
	List<QuestionOption> getOptionByQuestion(Long questionId) throws SQLException;
	QuestionOption insert (QuestionOption questionOption) throws SQLException;
	QuestionOption getById (Long optionId) throws SQLException;
}

