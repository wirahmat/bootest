package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.QuestionTypeDao;
import com.lawencon.bootcamptest.model.QuestionType;

public class QuestionTypeDaoHQLImpl implements QuestionTypeDao{
	private final EntityManager em;
	
	public QuestionTypeDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<QuestionType> getAll() throws SQLException {
		final String sql = "SELECT "
				+ "qt "
				+ "FROM "
				+ "QuestionType qt";
		
		final List<QuestionType> questionTypes = 
				this.em.createQuery(sql, QuestionType.class)
				.getResultList();
		return questionTypes;
	}
	
	@Override
	public QuestionType getById(Long typeId) throws SQLException {
		final QuestionType questionType = this.em.find(QuestionType.class, typeId);
		return questionType;
	}
}
