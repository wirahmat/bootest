package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.AcceptanceStatusDao;
import com.lawencon.bootcamptest.model.AcceptanceStatus;

public class AcceptanceStatusDaoImpl implements AcceptanceStatusDao{
	private final EntityManager em;

	public AcceptanceStatusDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public AcceptanceStatus getByCode(String statusCode) throws SQLException {
		final String sql ="SELECT "
				+ "id, status, acceptance_code "
				+ "FROM "
				+ "t_acceptance_status "
				+ "WHERE "
				+ "acceptance_code = :statusCode ";
		
		final Object acceptanceObj = this.em.createNativeQuery(sql)
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AcceptanceStatus> getAll() throws SQLException {
		final String sql ="SELECT "
				+ "* "
				+ "FROM "
				+ "t_acceptance_status ";
		
		final List<AcceptanceStatus> acceptanceStatus = 
				this.em.createNativeQuery(sql, AcceptanceStatus.class)
				.getResultList();
		
		return acceptanceStatus;
	}
	
	@Override
	public AcceptanceStatus getById(Long id) throws SQLException {
		final AcceptanceStatus acceptanceStatus = this.em.find(AcceptanceStatus.class, id);
		return acceptanceStatus;
	}
}
