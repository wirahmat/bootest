package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.FileDao;
import com.lawencon.bootcamptest.model.File;

public class FileDaoImpl implements FileDao{
	private final EntityManager em;
	
	public FileDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public File getById(Long id) throws SQLException {
		final File file = this.em.find(File.class, id);
		return file;
	}
	
	@Override
	public File insert(File file) throws SQLException {
		em.persist(file);
		return file;
	}
	
}
