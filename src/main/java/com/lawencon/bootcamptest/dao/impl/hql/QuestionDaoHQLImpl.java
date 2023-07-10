package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.QuestionDao;
import com.lawencon.bootcamptest.model.Question;

public class QuestionDaoHQLImpl implements QuestionDao{
	private final EntityManager em;
	
	public QuestionDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<Question> getAll() throws SQLException {
		final String sql ="SELECT "
				+ "q "
				+ "FROM "
				+ "Question q ";
		
		final List<Question> questions = 
				this.em.createQuery(sql, Question.class)
				.getResultList();
		return questions;
	}
	
	@Override
	public List<Question> getAllByType(Long questionTypeId) throws SQLException {
		final String sql ="SELECT "
				+ "q "
				+ "FROM "
				+ "Question q "
				+ "WHERE "
				+ "q.questionType.id = :questionTypeId";
		
		final List<Question> questions = 
				this.em.createQuery(sql, Question.class)
				.setParameter("questionTypeId", questionTypeId)
				.getResultList();
		return questions;
	}
	
	@Override
	public List<Question> getByIdCandidate(Long candidateId) throws SQLException {
		final String sql ="SELECT "
				+ "q "
				+ "FROM "
				+ "Question q "
				+ "INNER JOIN "
				+ "QuestionCandidate qc ON q.id = qc.question.id "
				+ "WHERE "
				+ "qc.candidate.id = :candidateId";
		
		final List<Question> questions = 
				this.em.createQuery(sql, Question.class)
				.setParameter("candidateId", candidateId)
				.getResultList();
		
		return questions;
	}
	
	@Override
	public List<Question> getByIdReviewer(Long reviewerId) throws SQLException {
		final String sql ="SELECT "
				+ "q "
				+ "FROM "
				+ "Question "
				+ "WHERE "
				+ "q.createdBy = :reviewerId";
		
		final List<Question> questions = 
				this.em.createQuery(sql, Question.class)
				.setParameter("reviewerId", reviewerId)
				.getResultList();
		
		return questions;
	}
	
	@Override
	public Question insert(Question question) throws SQLException {
		em.persist(question);
		return question;
	}
	
	@Override
	public Question getById(Long questionId) throws SQLException {
		final Question question = this.em.find(Question.class, questionId);
		return question;
	}
}
