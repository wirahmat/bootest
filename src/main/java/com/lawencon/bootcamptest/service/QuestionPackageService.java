package com.lawencon.bootcamptest.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.QuestionPackage;

public interface QuestionPackageService{
	List<QuestionPackage> getAll() throws SQLException;
	QuestionPackage insert(String packageName, String packageCode, Long hrId) throws SQLException;
}
