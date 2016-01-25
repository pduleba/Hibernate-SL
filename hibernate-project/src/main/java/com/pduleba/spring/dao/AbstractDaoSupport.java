package com.pduleba.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractDaoSupport<T> {

	private SessionFactory sessionFactory;
	private Class<T> type;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void create(T enity) {
		getSession().save(enity);
	}
	
	public void createAll(List<T> entities) {
		for(T entity : entities) {
			create(entity);
		}
	}

	public T read(long entityId) {
		return getSession().get(type, entityId);
	}

	public void update(T enity) {
		Session session = getSession();
		session.update(session.contains(enity) ? enity : session.merge(enity));
	}

	public void delete(T enity) {
		Session session = getSession();
		session.delete(session.contains(enity) ? enity : session.merge(enity));
	}
}
