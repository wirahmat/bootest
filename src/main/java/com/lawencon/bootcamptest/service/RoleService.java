package com.lawencon.bootcamptest.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.Role;

public interface RoleService {
	List<Role> getAll() throws SQLException;
	Role getByCode(String code) throws SQLException;
}
