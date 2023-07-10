package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.AcceptanceStatus;

public interface AcceptanceStatusDao {
	AcceptanceStatus getByCode(String statusCode) throws SQLException;
	List<AcceptanceStatus> getAll() throws SQLException;
	AcceptanceStatus getById(Long id) throws SQLException;
}
