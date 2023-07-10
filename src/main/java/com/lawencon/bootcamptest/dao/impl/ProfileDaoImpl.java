package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.ProfileDao;
import com.lawencon.bootcamptest.model.Profile;

public class ProfileDaoImpl implements ProfileDao{
	private final EntityManager em;
	
	public ProfileDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public Profile getById(Long id) throws SQLException {
		final Profile profile = this.em.find(Profile.class, id);
		return profile;
	}
	
	@Override
	public Profile insert(Profile profile) throws SQLException {
		em.persist(profile);
		return profile;
	}
}
