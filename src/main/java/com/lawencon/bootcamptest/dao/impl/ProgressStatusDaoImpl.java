package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.ProgressStatusDao;
import com.lawencon.bootcamptest.model.ProgressStatus;

public class ProgressStatusDaoImpl implements ProgressStatusDao{
	private final EntityManager em;
	
	public ProgressStatusDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public ProgressStatus getByCode(String progressCode) throws SQLException {
		final String sql ="SELECT "
				+ "id, status, progress_code "
				+ "FROM "
				+ "t_progress_status "
				+ "WHERE "
				+ "progress_code = :progressCode ";
		
		final Object progressObj = this.em.createNativeQuery(sql)
				.setParameter("progressCode", progressCode)
				.getSingleResult();
		
		final Object[] progressArr = (Object[]) progressObj;
		
		ProgressStatus progressStatus = null;

		if(progressArr.length > 0) {
			progressStatus = new ProgressStatus();

			progressStatus.setId(Long.valueOf(progressArr[0].toString()));
			progressStatus.setStatus(progressArr[1].toString());
			progressStatus.setProgressCode(progressArr[2].toString());
		}
		return progressStatus;
	}
	@Override
	public ProgressStatus getById(Long id) throws SQLException {
		final ProgressStatus progressStatus = this.em.find(ProgressStatus.class, id);
		return progressStatus;
	}
}
