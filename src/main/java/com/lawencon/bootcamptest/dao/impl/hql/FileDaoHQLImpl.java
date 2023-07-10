package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.FileDao;
import com.lawencon.bootcamptest.model.File;

public class FileDaoHQLImpl implements FileDao{
	private final EntityManager em;
	
	public FileDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
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
