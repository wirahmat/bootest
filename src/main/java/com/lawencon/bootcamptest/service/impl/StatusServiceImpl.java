package com.lawencon.bootcamptest.service.impl;

import java.sql.SQLException;
import java.util.List;


import com.lawencon.bootcamptest.dao.AcceptanceStatusDao;
import com.lawencon.bootcamptest.dao.ProgressStatusDao;
import com.lawencon.bootcamptest.model.AcceptanceStatus;
import com.lawencon.bootcamptest.model.ProgressStatus;
import com.lawencon.bootcamptest.service.StatusService;

public class StatusServiceImpl implements StatusService{
	private final AcceptanceStatusDao acceptanceStatusDao;
	private final ProgressStatusDao progressStatusDao;
	
	public StatusServiceImpl(AcceptanceStatusDao acceptanceStatusDaoImpl, 
			ProgressStatusDao progressStatusDaoImpl) throws SQLException{
		this.acceptanceStatusDao = acceptanceStatusDaoImpl;
		this.progressStatusDao = progressStatusDaoImpl;
	}
	
	@Override
	public AcceptanceStatus getAcceptanceByCode(String acceptanceCode) throws SQLException {
		final AcceptanceStatus acceptanceStatus = acceptanceStatusDao.getByCode(acceptanceCode);
		return acceptanceStatus;
	}
	
	@Override
	public ProgressStatus getProgressByCode(String progressCode) throws SQLException {
		final ProgressStatus progressStatus = progressStatusDao.getByCode(progressCode);
		return progressStatus;
	}
	
	@Override
	public List<AcceptanceStatus> getAll() throws SQLException {
		final List<AcceptanceStatus> acceptanceStatus = acceptanceStatusDao.getAll();
		return acceptanceStatus;
	}
}
