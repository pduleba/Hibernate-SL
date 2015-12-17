package com.pduleba.spring.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.pduleba.hibernate.model.UserModel;

@Repository
@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Override
	public List<UserModel> getAllUsers() {
		
		List<UserModel> allUsers = getHibernateTemplate().loadAll(UserModel.class);
//		for (UserModel um : allUsers) {
//			Hibernate.initialize(um.getOrders());
//		}

		return allUsers;
	}
	
	@Override
	public Serializable save(UserModel user) {
		return getHibernateTemplate().save(user);
	}
	
	@Override
	public void removeAll(List<UserModel> users) {
		getHibernateTemplate().deleteAll(users);
	}
}
