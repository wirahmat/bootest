package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.QuestionCandidateDao;
import com.lawencon.bootcamptest.model.QuestionCandidate;


public class QuestionCandidateDaoImpl implements QuestionCandidateDao{
	
	private final EntityManager em;
	
	public QuestionCandidateDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionCandidate> getByCandidate(Long candidateId) throws SQLException {
		final String sql ="SELECT "
				+ "* "
				+ "FROM "
				+ "t_question_candidate "
				+ "WHERE "
				+ "candidate_id = :candidateId";
		
		final List<QuestionCandidate> questionCandidates = 
				this.em.createNativeQuery(sql, QuestionCandidate.class)
				.setParameter("candidateId", candidateId)
				.getResultList();
		
		return questionCandidates;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionCandidate> getByType(String typeCode) throws SQLException {
		final String sql ="SELECT "
				+ "	* "
				+ "FROM "
				+ "	t_question_candidate tqc "
				+ "INNER JOIN "
				+ "	t_question tq ON tqc.question_id = tq.id "
				+ "INNER JOIN "
				+ "	t_candidate_assign tca ON tca.question_type_id = tq.type_id "
				+ "INNER JOIN "
				+ " t_question_type tqt ON tca.question_type_id = tqt.id "
				+ "WHERE "
				+ "	tqt.type_code = :typeCode ";
		
		final List<QuestionCandidate> questionCandidates = 
				this.em.createNativeQuery(sql, QuestionCandidate.class)
				.setParameter("typeCode", typeCode)
				.getResultList();
		
		return questionCandidates;
	}
	
	@Override
	public QuestionCandidate insert(QuestionCandidate questionCandidate) throws SQLException {
		em.persist(questionCandidate);
		return questionCandidate;
	}
	
	@Override
	public QuestionCandidate getById(Long id) throws SQLException {
		final QuestionCandidate questionCandidate = this.em.find(QuestionCandidate.class, id);
		return questionCandidate;
	}
}
