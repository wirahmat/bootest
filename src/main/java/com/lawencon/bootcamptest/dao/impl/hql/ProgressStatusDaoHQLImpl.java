package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.ProgressStatusDao;
import com.lawencon.bootcamptest.model.ProgressStatus;

public class ProgressStatusDaoHQLImpl implements ProgressStatusDao{
	private final EntityManager em;
	
	public ProgressStatusDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public ProgressStatus getByCode(String progressCode) throws SQLException {
		final String sql ="SELECT "
				+ "ps.id, ps.status, ps.progressCode "
				+ "FROM "
				+ "ProgressStatus ps "
				+ "WHERE "
				+ "ps.progressCode = :progressCode ";
		
		final Object progressObj = this.em.createQuery(sql)
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
