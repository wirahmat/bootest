package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.QuestionCandidateDao;
import com.lawencon.bootcamptest.model.QuestionCandidate;


public class QuestionCandidateDaoHQLImpl implements QuestionCandidateDao{
	
	private final EntityManager em;
	
	public QuestionCandidateDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<QuestionCandidate> getByCandidate(Long candidateId) throws SQLException {
		final String sql ="SELECT "
				+ "qc "
				+ "FROM "
				+ "QuestionCandidate qc "
				+ "WHERE "
				+ "qc.candidate.id = :candidateId";
		
		final List<QuestionCandidate> questionCandidates = 
				this.em.createQuery(sql, QuestionCandidate.class)
				.setParameter("candidateId", candidateId)
				.getResultList();
		
		return questionCandidates;
	}
	
	@Override
	public List<QuestionCandidate> getByType(String typeCode) throws SQLException {
		final String sql ="SELECT "
				+ "qc "
				+ "FROM " 
				+ "QuestionCandidate qc "
				+ "WHERE "
				+ "qc.question.questionType.typeCode = :typeCode ";
		
		final List<QuestionCandidate> questionCandidates = 
				this.em.createQuery(sql, QuestionCandidate.class)
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
