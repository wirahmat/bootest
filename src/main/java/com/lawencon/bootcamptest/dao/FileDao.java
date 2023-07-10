package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;

import com.lawencon.bootcamptest.model.File;

public interface FileDao {
	File getById(Long id) throws SQLException;
	File insert(File file) throws SQLException;
}
