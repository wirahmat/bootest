package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.QuestionPackageDao;
import com.lawencon.bootcamptest.model.QuestionPackage;

public class QuestionPackageDaoHQLImpl implements QuestionPackageDao{
	private final EntityManager em;
	
	public QuestionPackageDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<QuestionPackage> getAll() throws SQLException {
		final String sql ="SELECT "
				+ "qp "
				+ "FROM "
				+ "QuestionPackage qp ";
		
		final List<QuestionPackage> questionPackages = 
				this.em.createQuery(sql, QuestionPackage.class)
				.getResultList();
		return questionPackages;
	}
	
	@Override
	public QuestionPackage insert(QuestionPackage questionPackage) throws SQLException {
		em.persist(questionPackage);
		return questionPackage;
	}
	
	@Override
	public QuestionPackage getById(Long packageId) throws SQLException {
		final QuestionPackage questionPackage = this.em.find(QuestionPackage.class, packageId);
		return questionPackage;
	}
}
