package com.lawencon.bootcamptest.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.AcceptanceStatus;
import com.lawencon.bootcamptest.model.ProgressStatus;

public interface StatusService {
	AcceptanceStatus getAcceptanceByCode(String acceptanceCode) throws SQLException;
	ProgressStatus getProgressByCode(String progressCode) throws SQLException;
	List<AcceptanceStatus> getAll() throws SQLException;
}