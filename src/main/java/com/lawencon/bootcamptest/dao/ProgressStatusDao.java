package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;

import com.lawencon.bootcamptest.model.ProgressStatus;

public interface ProgressStatusDao {
	ProgressStatus getByCode(String code) throws SQLException;
	ProgressStatus getById(Long id) throws SQLException;
}
