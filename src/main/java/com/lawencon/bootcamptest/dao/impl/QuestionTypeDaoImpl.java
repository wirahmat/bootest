package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.QuestionTypeDao;
import com.lawencon.bootcamptest.model.QuestionType;

public class QuestionTypeDaoImpl implements QuestionTypeDao{
	private final EntityManager em;
	
	public QuestionTypeDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionType> getAll() throws SQLException {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_question_type";
		
		final List<QuestionType> questionTypes = 
				this.em.createNativeQuery(sql, QuestionType.class)
				.getResultList();
		return questionTypes;
	}
	
	@Override
	public QuestionType getById(Long typeId) throws SQLException {
		final QuestionType questionType = this.em.find(QuestionType.class, typeId);
		return questionType;
	}
}
