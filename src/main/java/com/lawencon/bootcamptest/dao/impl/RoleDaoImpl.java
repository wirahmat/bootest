package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.RoleDao;
import com.lawencon.bootcamptest.model.Role;

public class RoleDaoImpl implements RoleDao{
	private final EntityManager em;
	
	public RoleDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() throws SQLException{
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_user_role";
		final List<Role> roles = this.em.createNativeQuery(sql, Role.class).getResultList();
		return roles;
	}
	
	@Override
	public Role getByCode(String code) throws SQLException {
		final String sql = "SELECT "
				+ "id, role_name, role_code "
				+ "FROM "
				+ "t_user_role "
				+ "WHERE "
				+ "role_code = :roleCode";
		
		final Object roleObj = this.em.createNativeQuery(sql)
				.setParameter("roleCode", code)
				.getSingleResult();
		
		final Object[] roleArr = (Object[]) roleObj;
		
		Role role = null;

		if(roleArr.length > 0) {
			role = new Role();
			role.setId(Long.valueOf(roleArr[0].toString()));			
			role.setRoleName(roleArr[1].toString());
			role.setRoleCode(roleArr[2].toString());		
		}
		return role;
	}
	@Override
	public Role getById(Long roleId) throws SQLException {
		final Role role = this.em.find(Role.class, roleId);
		return role;
	}
}
