package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.QuestionPackage;

public interface QuestionPackageDao {
	List<QuestionPackage> getAll() throws SQLException;
	QuestionPackage insert(QuestionPackage questionPackage) throws SQLException;
	QuestionPackage getById(Long packageId) throws SQLException;
}
