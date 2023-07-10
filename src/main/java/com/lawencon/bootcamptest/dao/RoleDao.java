package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.Role;

public interface RoleDao {
	List<Role> getAll() throws SQLException;
	Role getByCode(String code) throws SQLException;
	Role getById(Long roleId) throws SQLException;
}
