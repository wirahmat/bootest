package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.QuestionOptionDao;
import com.lawencon.bootcamptest.model.QuestionOption;

public class QuestionOptionDaoImpl implements QuestionOptionDao{
	private final EntityManager em;
	
	public QuestionOptionDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionOption> getOptionByQuestion(Long questionId) throws SQLException {
		final String sql ="SELECT "
				+ "* "
				+ "FROM "
				+ "t_question_option qo "
				+ "INNER JOIN "
				+ "t_question q ON q.id = qo.question_id "
				+ "WHERE "
				+ "qo.question_id = :questionId";
		
		final List<QuestionOption> questionOptions = 
				this.em.createNativeQuery(sql, QuestionOption.class)
				.setParameter("questionId", questionId)
				.getResultList();
		return questionOptions;
	}
	
	@Override
	public QuestionOption insert(QuestionOption questionOption) throws SQLException {
		em.persist(questionOption);
		return questionOption;
	}
	
	@Override
	public QuestionOption getById(Long optionId) throws SQLException {
		final QuestionOption questionOption = this.em.find(QuestionOption.class, optionId);
		return questionOption;
	}
}
