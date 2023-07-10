package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;

import com.lawencon.bootcamptest.model.Profile;

public interface ProfileDao {
	Profile getById(Long id) throws SQLException;
	Profile insert(Profile profile) throws SQLException;
}
