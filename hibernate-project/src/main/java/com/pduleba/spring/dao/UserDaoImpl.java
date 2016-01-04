package com.pduleba.spring.dao;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.pduleba.hibernate.model.UserModel;

@Repository
@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	
	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void saveAll(Collection<UserModel> users) {
		for (UserModel user : users) {
			getHibernateTemplate().saveOrUpdate(user);
		}
	}

	@Override
	public List<UserModel> getAllUsers() {
		return getHibernateTemplate().loadAll(UserModel.class);
	}

	@Override
	public void removeAll(List<UserModel> users) {
		getHibernateTemplate().deleteAll(users);
	}

}
