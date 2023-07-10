package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.QuestionTopic;

public interface QuestionTopicDao {
	List<QuestionTopic> getAll() throws SQLException;
	QuestionTopic insert(QuestionTopic questionTopic) throws SQLException;
	QuestionTopic getById(Long topicId) throws SQLException; 
}
