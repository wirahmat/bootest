package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.FileTypeDao;
import com.lawencon.bootcamptest.model.FileType;

public class FileTypeDaoImpl implements FileTypeDao{
	private final EntityManager em;
	
	public FileTypeDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FileType> getAll() throws SQLException {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_file_type";
		
		final List<FileType> fileTypes = 
				this.em.createNativeQuery(sql, FileType.class).getResultList();
		
		return fileTypes;
	}
	
	@Override
	public FileType getById(Long id) throws SQLException {
		final FileType fileType = this.em.find(FileType.class, id);
		return fileType;
	}
}
