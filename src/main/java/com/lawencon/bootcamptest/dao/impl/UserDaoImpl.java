package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.UserDao;
import com.lawencon.bootcamptest.model.Profile;
import com.lawencon.bootcamptest.model.Role;
import com.lawencon.bootcamptest.model.User;

public class UserDaoImpl implements UserDao{
	private final EntityManager em;
	
	public UserDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() throws SQLException{
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_user";
		
		final List<User> users = 
				this.em.createNativeQuery(sql, User.class)
				.getResultList();
		return users;
	}
	
	@Override
	public List<User> getByRoleCode(String roleCode) throws SQLException {
		final List<User> users = new ArrayList<>();
		
		final String sql ="SELECT "
				+ "u.id as user_id, role_code, user_name "
				+ "FROM "
				+ "t_user u "
				+ "INNER JOIN "
				+ "t_user_role r ON r.id = u.role_id "
				+ "INNER JOIN "
				+ "t_profile p ON p.id = u.profile_id "
				+ "WHERE "
				+ "r.role_code = :roleCode ";
		
		final List<?> userObjs = this.em.createNativeQuery(sql)
			.setParameter("roleCode", roleCode)
			.getResultList();
		
		if(userObjs.size() > 0) {
			for(Object userObj : userObjs) {
				final Object[] userArr = (Object[]) userObj;
				
				final Profile profile = new Profile();
				profile.setUserName(userArr[2].toString());
				
				final User user = new User();
				user.setId(Long.valueOf(userArr[0].toString()));
				user.setProfile(profile);
							
				final Role role = new Role();
				role.setRoleCode(userArr[1].toString());
				user.setRole(role);
				users.add(user);
			}
		}
		
		return users;
	}
	
	@Override
	public User insert(User user) throws SQLException {
		em.persist(user);
		return user;
	}
	
	@Override
	public User getByUserNameAndPassword(String userName, String userPassword) throws SQLException {
		final String sql = "SELECT "
				+ "u.id AS user_id, user_name, role_code, u.is_active  "
				+ "FROM "
				+ "t_user u "
				+ "INNER JOIN "
				+ "t_user_role r ON r.id = u.role_id "
				+ "INNER JOIN "
				+ "t_profile p ON p.id = u.profile_id "
				+ "WHERE "
				+ "u.user_email = :userName AND u.user_password = :userPassword";
		try {
			final Object userObj = this.em.createNativeQuery(sql)
					.setParameter("userPassword", userPassword)
					.setParameter("userName", userName)
					.getSingleResult();
			
			final Object[] userArr = (Object[]) userObj;
			
			User user = null;

			if(userArr.length > 0) {
				user = new User();
				user.setId(Long.valueOf(userArr[0].toString()));
							
				final Profile profile = new Profile();
				profile.setUserName(userArr[1].toString());
				user.setProfile(profile);
				
				final Role role = new Role();
				role.setRoleCode(userArr[2].toString());
				user.setRole(role);
				
				user.setIsActive(Boolean.valueOf(userArr[3].toString()));
			
			}
			return user;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public User getById(Long userId) throws SQLException {
		final User user = this.em.find(User.class, userId);
		return user;
	}
}
