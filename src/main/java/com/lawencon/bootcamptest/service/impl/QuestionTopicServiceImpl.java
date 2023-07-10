package com.lawencon.bootcamptest.service.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.QuestionTopicDao;
import com.lawencon.bootcamptest.model.QuestionTopic;
import com.lawencon.bootcamptest.service.QuestionTopicService;

public class QuestionTopicServiceImpl implements QuestionTopicService{
	private final QuestionTopicDao questionTopicDao;
	private final EntityManager em;
	
	public QuestionTopicServiceImpl(QuestionTopicDao questionTopicDao, SessionFactory sessionFactory) throws SQLException {
		this.questionTopicDao = questionTopicDao;
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<QuestionTopic> getAllTopic() throws SQLException {
		final List<QuestionTopic> questionTopics = questionTopicDao.getAll();
		return questionTopics;
	}
	
	@Override
	public QuestionTopic insert(String topicName, String topicCode, Long reviewerId) throws SQLException {
		this.em.getTransaction().begin();
		
		final QuestionTopic questionTopic = new QuestionTopic();
		questionTopic.setTopicName(topicName);
		questionTopic.setTopicCode(topicCode.toUpperCase());
		questionTopic.setCreatedBy(reviewerId);
		
		final LocalDateTime timeNow = LocalDateTime.now();
		questionTopic.setCreatedAt(timeNow);
		questionTopic.setIsActive(true);
		questionTopic.setVersionNum(1);
		
		final QuestionTopic questionTopicResult = questionTopicDao.insert(questionTopic);
		this.em.getTransaction().commit();
		return questionTopicResult;
	}
}
