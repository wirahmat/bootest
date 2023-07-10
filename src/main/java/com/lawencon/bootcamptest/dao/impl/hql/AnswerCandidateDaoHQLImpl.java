package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.AnswerCandidateDao;
import com.lawencon.bootcamptest.model.AnswerCandidate;

public class AnswerCandidateDaoHQLImpl implements AnswerCandidateDao {
	private final EntityManager em;

	public AnswerCandidateDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public AnswerCandidate insert(AnswerCandidate answerCandidate) throws SQLException {
		em.persist(answerCandidate);
		return answerCandidate;
	}
	
	@Override
	public List<AnswerCandidate> getByType(String typeCode) throws SQLException {
		final String sql ="SELECT "
				+ "ac "
				+ "FROM " 
				+ "AnswerCandidate ac "
				+ "WHERE "
				+ "ac.candidateAssign.questionType.typeCode = :typeCode";
		
		final List<AnswerCandidate> answerCandidates = 
				this.em.createQuery(sql, AnswerCandidate.class)
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
