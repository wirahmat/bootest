package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.AcceptanceStatusDao;
import com.lawencon.bootcamptest.model.AcceptanceStatus;

public class AcceptanceStatusDaoHQLImpl implements AcceptanceStatusDao{
	private final EntityManager em;

	public AcceptanceStatusDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public AcceptanceStatus getByCode(String statusCode) throws SQLException {
		final String sql ="SELECT "
				+ "ac.id, ac.status, ac.acceptanceCode "
				+ "FROM "
				+ "AcceptanceStatus ac "
				+ "WHERE "
				+ "ac.acceptanceCode = :statusCode ";
		
		final Object acceptanceObj = this.em.createQuery(sql)
				.setParameter("statusCode", statusCode)
				.getSingleResult();
		
		final Object[] acceptanceArr = (Object[]) acceptanceObj;
		
		AcceptanceStatus acceptanceStatus= null;

		if(acceptanceArr.length > 0) {
			acceptanceStatus = new AcceptanceStatus();

			acceptanceStatus.setId(Long.valueOf(acceptanceArr[0].toString()));
			acceptanceStatus.setStatus(acceptanceArr[1].toString());
			acceptanceStatus.setAcceptanceCode(acceptanceArr[2].toString());
		}
		return acceptanceStatus;
	}
	
	@Override
	public List<AcceptanceStatus> getAll() throws SQLException {
		final String sql ="SELECT "
				+ "ac "
				+ "FROM "
				+ "AcceptanceStatus ac";
		
		final List<AcceptanceStatus> acceptanceStatus = 
				this.em.createQuery(sql, AcceptanceStatus.class)
				.getResultList();
		
		return acceptanceStatus;
	}
	
	@Override
	public AcceptanceStatus getById(Long id) throws SQLException {
		final AcceptanceStatus acceptanceStatus = this.em.find(AcceptanceStatus.class, id);
		return acceptanceStatus;
	}
}
