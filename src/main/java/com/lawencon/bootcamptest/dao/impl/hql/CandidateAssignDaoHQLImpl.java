package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.CandidateAssignDao;
import com.lawencon.bootcamptest.model.CandidateAssign;

public class CandidateAssignDaoHQLImpl implements CandidateAssignDao{
	
	private final EntityManager em;

	public CandidateAssignDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}

	
	@Override
	public CandidateAssign insert(CandidateAssign candidateAssign) throws SQLException {
		em.persist(candidateAssign);
		return candidateAssign;
	}
	
	@Override
	public List<CandidateAssign> getById(Long candidateId) throws SQLException {
		final String sql = "SELECT "
				+ "ca "
				+ "FROM "
				+ "CandidateAssign ca "
				+ "WHERE "
				+ "ca.candidate.id = :candidateId";
		
		final List<CandidateAssign> candidateAssigns = 
				this.em.createQuery(sql, CandidateAssign.class)
				.setParameter("candidateId", candidateId)
				.getResultList();
		
		return candidateAssigns;
	}
}
