package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.AnswerCandidateDao;
import com.lawencon.bootcamptest.model.AnswerCandidate;

public class AnswerCandidateDaoImpl implements AnswerCandidateDao {
	private final EntityManager em;

	public AnswerCandidateDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public AnswerCandidate insert(AnswerCandidate answerCandidate) throws SQLException {
		em.persist(answerCandidate);
		return answerCandidate;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AnswerCandidate> getByType(String typeCode) throws SQLException {
		final String sql ="SELECT "
				+ "* "
				+ "FROM "
				+ "t_answer_candidate tac "
				+ "INNER JOIN "
				+ "t_candidate_assign tca ON tac.candidate_assign_id = tca.id "
				+ "INNER JOIN "
				+ "t_question_type tqt ON tqt.id = tca.question_type_id "
				+ "WHERE "
				+ "tqt.type_code = :typeCode";
		
		final List<AnswerCandidate> answerCandidates = 
				this.em.createNativeQuery(sql, AnswerCandidate.class)
				.setParameter("typeCode", typeCode)
				.getResultList();
		
		return answerCandidates;
	}
	
	@Override
	public AnswerCandidate getById(Long id) throws SQLException {
		final AnswerCandidate answerCandidate = this.em.find(AnswerCandidate.class, id);
		return answerCandidate;
	}
}
