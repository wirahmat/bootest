package com.lawencon.bootcamptest.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.File;
import com.lawencon.bootcamptest.model.Profile;
import com.lawencon.bootcamptest.model.User;

public interface UserService {
	List<User> getAll() throws SQLException;
	List<User> getByRoleCode(String roleCode) throws SQLException;
	User insert(User user, Profile profile, File file) throws SQLException;
	User login(String userName, String userPassword) throws SQLException;
	User getById(Long userId) throws SQLException;
	User updateUser(User user) throws SQLException;
}
