package com.lawencon.bootcamptest.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.QuestionTopic;

public interface QuestionTopicService {
	List<QuestionTopic> getAllTopic() throws SQLException;
	QuestionTopic insert(String topicName, String topicCode, Long reviewerId) throws SQLException;
}
