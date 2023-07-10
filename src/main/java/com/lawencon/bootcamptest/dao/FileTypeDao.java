package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.FileType;

public interface FileTypeDao {
	List<FileType> getAll() throws SQLException;
	FileType getById(Long id) throws SQLException;
}
