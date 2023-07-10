package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.RoleDao;
import com.lawencon.bootcamptest.model.Role;

public class RoleDaoHQLImpl implements RoleDao{
	private final EntityManager em;
	
	public RoleDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<Role> getAll() throws SQLException{
		final String sql = "SELECT "
				+ "r "
				+ "FROM "
				+ "Role r";
		final List<Role> roles = this.em.createQuery(sql, Role.class).getResultList();
		return roles;
	}
	
	@Override
	public Role getByCode(String code) throws SQLException {
		final String sql = "SELECT "
				+ "r.id, r.roleName, r.roleCode "
				+ "FROM "
				+ "Role r "
				+ "WHERE "
				+ "r.roleCode = :roleCode";
		
		final Object roleObj = this.em.createQuery(sql)
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
		// TODO Auto-generated method stub
		return null;
	}
}
