package com.lawencon.bootcamptest.config;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

public class EntityManagerConfig {
	private static final ThreadLocal<EntityManager> ENTITY_NAMAGER = new ThreadLocal<>();
	
	public static EntityManager get(SessionFactory sessionFactory) {
		final EntityManager em = ENTITY_NAMAGER.get();
		if(em != null) {
			return em; 
		}
		final EntityManager emNew = sessionFactory.createEntityManager();
		ENTITY_NAMAGER.set(emNew);
		return emNew;
	}
	
}
