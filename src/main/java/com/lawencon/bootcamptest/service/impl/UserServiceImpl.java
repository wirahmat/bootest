package com.lawencon.bootcamptest.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.FileDao;
import com.lawencon.bootcamptest.dao.ProfileDao;
import com.lawencon.bootcamptest.dao.UserDao;
import com.lawencon.bootcamptest.model.File;
import com.lawencon.bootcamptest.model.Profile;
import com.lawencon.bootcamptest.model.User;
import com.lawencon.bootcamptest.service.UserService;


public class UserServiceImpl implements UserService{
	
	private final UserDao userDao;
	private final ProfileDao profileDao;
	private final FileDao fileDao;
	private final EntityManager em;
	
	public UserServiceImpl(UserDao userDao, ProfileDao profileDao, 
			FileDao fileDao, SessionFactory sessionFactory) throws SQLException {
		this.userDao = userDao;
		this.profileDao = profileDao;
		this.fileDao = fileDao;
		this.em = EntityManagerConfig.get(sessionFactory);
	}

	@Override
	public List<User> getAll() throws SQLException {
		final List<User> users = userDao.getAll();
		return users;
	}

	
	@Override
	public List<User> getByRoleCode(String roleCode) throws SQLException {
		final List<User> users = userDao.getByRoleCode(roleCode);
		return users;
	}
	
	@Override
	public User insert(User user, Profile profile, File file) throws SQLException {
		User userResult = null;
		try {
			this.em.getTransaction().begin();
			if(profile.getFile() != null) {
				final File fileResult = fileDao.insert(file);
				profile.setFile(fileResult);
			}
			
			final Profile profileResult = profileDao.insert(profile);
			user.setProfile(profileResult);
			userResult = userDao.insert(user);
			this.em.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
		return userResult;
	}
	
	@Override
	public User login(String userName, String userPassword) throws SQLException {
		final User user = userDao.getByUserNameAndPassword(userName, userPassword);
		return user;
	}
	
	@Override
	public User getById(Long userId) throws SQLException {
		final User user = userDao.getById(userId);
		return user;
	}
	
	@Override
	public User updateUser(User user) throws SQLException {
		this.em.getTransaction().begin();
		
		final User userDb = userDao.getById(user.getId());
		userDb.setIsActive(user.getIsActive());
		
		this.em.getTransaction().commit();
		return userDb;
	}
	
}
