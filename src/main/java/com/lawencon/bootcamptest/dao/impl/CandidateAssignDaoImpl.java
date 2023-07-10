package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.CandidateAssignDao;
import com.lawencon.bootcamptest.model.CandidateAssign;

public class CandidateAssignDaoImpl implements CandidateAssignDao{
	
	private final EntityManager em;

	public CandidateAssignDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}

	
	@Override
	public CandidateAssign insert(CandidateAssign candidateAssign) throws SQLException {
		em.persist(candidateAssign);
		return candidateAssign;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CandidateAssign> getById(Long candidateId) throws SQLException {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_candidate_assign "
				+ "WHERE "
				+ "candidate_id = :candidateId";
		
		final List<CandidateAssign> candidateAssigns = 
				this.em.createNativeQuery(sql, CandidateAssign.class)
				.setParameter("candidateId", candidateId)
				.getResultList();
		
		return candidateAssigns;
	}
}
