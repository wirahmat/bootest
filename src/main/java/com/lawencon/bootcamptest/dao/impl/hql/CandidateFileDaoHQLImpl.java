package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.CandidateFileDao;
import com.lawencon.bootcamptest.model.CandidateFile;

public class CandidateFileDaoHQLImpl implements CandidateFileDao{
	private final EntityManager em;
	
	public CandidateFileDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}

	@Override
	public CandidateFile insert(CandidateFile candidateFile) throws SQLException {
		em.persist(candidateFile);
		return candidateFile;
	}
}
