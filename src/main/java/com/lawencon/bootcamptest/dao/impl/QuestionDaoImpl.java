package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.QuestionDao;
import com.lawencon.bootcamptest.model.Question;

public class QuestionDaoImpl implements QuestionDao{
	private final EntityManager em;
	
	public QuestionDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAll() throws SQLException {
		final String sql ="SELECT "
				+ "* "
				+ "FROM "
				+ "t_question tq "
				+ "INNER JOIN "
				+ "t_question_type tqt ON tq.type_id = tqt.id ";
		
		final List<Question> questions = 
				this.em.createNativeQuery(sql, Question.class)
				.getResultList();
		return questions;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAllByType(Long questionTypeId) throws SQLException {
		final String sql ="SELECT "
				+ "* "
				+ "FROM "
				+ "t_question q "
				+ "INNER JOIN "
				+ "t_question_type qt ON qt.id = q.type_id "
				+ "WHERE "
				+ "q.type_id = :questionTypeId";
		
		final List<Question> questions = 
				this.em.createNativeQuery(sql, Question.class)
				.setParameter("questionTypeId", questionTypeId)
				.getResultList();
		return questions;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getByIdCandidate(Long candidateId) throws SQLException {
		final String sql ="SELECT "
				+ "* "
				+ "FROM "
				+ "t_question tq "
				+ "INNER JOIN "
				+ "t_question_candidate tqc ON tq.id = tqc.question_id "
				+ "WHERE "
				+ "tqc.candidate_id = :candidateId";
		
		final List<Question> questions = 
				this.em.createNativeQuery(sql, Question.class)
				.setParameter("candidateId", candidateId)
				.getResultList();
		
		return questions;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getByIdReviewer(Long reviewerId) throws SQLException {
		final String sql ="SELECT "
				+ "* "
				+ "FROM "
				+ "t_question "
				+ "WHERE "
				+ "created_by = :reviewerId";
		
		final List<Question> questions = 
				this.em.createNativeQuery(sql, Question.class)
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
