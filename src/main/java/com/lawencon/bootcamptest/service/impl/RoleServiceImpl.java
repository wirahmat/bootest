package com.lawencon.bootcamptest.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lawencon.bootcamptest.constant.RoleEnum;
import com.lawencon.bootcamptest.dao.RoleDao;
import com.lawencon.bootcamptest.model.Role;
import com.lawencon.bootcamptest.service.RoleService;

public class RoleServiceImpl implements RoleService{
	private final RoleDao roleDao;
	
	private RoleServiceImpl(RoleDao roleDao) throws SQLException  {
		this.roleDao = roleDao;
	}
	
	@Override
	public List<Role> getAll() throws SQLException {
		final List<Role> roles = roleDao.getAll();
		final List<Role> roleResult = new ArrayList<>();
		for (int i = 0; i < roles.size(); i++) {
			if(RoleEnum.HR.roleCode.equalsIgnoreCase(roles.get(i).getRoleCode())) {
				roleResult.add(roles.get(i));
			}else if (RoleEnum.REVIEWER.roleCode.equalsIgnoreCase(roles.get(i).getRoleCode())){
				roleResult.add(roles.get(i));
			}
		}
		
		return roleResult;
	}
	
	@Override
	public Role getByCode(String code) throws SQLException {
		final Role role = roleDao.getByCode(code);
		return role;
	}
	
}
