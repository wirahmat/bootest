package com.lawencon.bootcamptest.service.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.QuestionPackageDao;
import com.lawencon.bootcamptest.model.QuestionPackage;
import com.lawencon.bootcamptest.service.QuestionPackageService;

public class QuestionPackageServiceImpl implements QuestionPackageService{
	private final QuestionPackageDao questionPackageDao;
	private final EntityManager em;
	
	public QuestionPackageServiceImpl(QuestionPackageDao questionPackageDao, 
			SessionFactory sessionFactory) throws SQLException{
		this.questionPackageDao = questionPackageDao;
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<QuestionPackage> getAll() throws SQLException {
		final List<QuestionPackage> questionPackages = questionPackageDao.getAll();
		return questionPackages;
	}
	
	
	@Override
	public QuestionPackage insert(String packageName, String packageCode, Long hrId) throws SQLException {
		this.em.getTransaction().begin();
		
		final QuestionPackage questionPackage = new QuestionPackage();
		questionPackage.setPackageName(packageName);
		questionPackage.setPackageCode(packageCode.toUpperCase());
		questionPackage.setCreatedBy(hrId);
		
		final LocalDateTime timeNow = LocalDateTime.now();
		questionPackage.setCreatedAt(timeNow);
		questionPackage.setIsActive(true);
		questionPackage.setVersionNum(1);
		
		final QuestionPackage questionPackageResult = questionPackageDao.insert(questionPackage);
		this.em.getTransaction().commit();
		return questionPackageResult;
	}
}
