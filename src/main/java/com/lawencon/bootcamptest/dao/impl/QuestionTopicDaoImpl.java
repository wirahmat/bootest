package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.QuestionTopicDao;
import com.lawencon.bootcamptest.model.QuestionTopic;

public class QuestionTopicDaoImpl implements QuestionTopicDao{
	
	private final EntityManager em;
	
	public QuestionTopicDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionTopic> getAll() throws SQLException {
		final String sql ="SELECT "
				+ "* "
				+ "FROM "
				+ "t_question_topic ";
		
		final List<QuestionTopic> questionTopics = 
				this.em.createNativeQuery(sql, QuestionTopic.class)
				.getResultList();
		return questionTopics;
	}
	
	@Override
	public QuestionTopic insert(QuestionTopic questionTopic) throws SQLException {
		em.persist(questionTopic);
		return questionTopic;
	}
	
	@Override
	public QuestionTopic getById(Long topicId) throws SQLException {
		final QuestionTopic questionTopic = this.em.find(QuestionTopic.class, topicId);
		return questionTopic;
	}
}
