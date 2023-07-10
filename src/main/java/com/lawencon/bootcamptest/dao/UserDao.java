package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.User;

public interface UserDao {
	List<User> getAll() throws SQLException;
	List<User> getByRoleCode(String roleCode) throws SQLException;
	User insert(User user) throws SQLException;
	User getByUserNameAndPassword(String userName, String userPassword) throws SQLException;
	User getById(Long userId) throws SQLException;
}
