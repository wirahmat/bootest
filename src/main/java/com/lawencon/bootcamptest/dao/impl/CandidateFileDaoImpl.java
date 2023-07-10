package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.CandidateFileDao;
import com.lawencon.bootcamptest.model.CandidateFile;

public class CandidateFileDaoImpl implements CandidateFileDao{
	private final EntityManager em;
	
	public CandidateFileDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}

	@Override
	public CandidateFile insert(CandidateFile candidateFile) throws SQLException {
		em.persist(candidateFile);
		return candidateFile;
	}
}
