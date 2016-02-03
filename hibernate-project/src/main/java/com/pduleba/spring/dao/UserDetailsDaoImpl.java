package com.pduleba.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.pduleba.hibernate.model.UserDetailsModel;

@Repository
@Transactional
public class UserDetailsDaoImpl extends HibernateDaoSupport implements UserDetailsDao {

	@Autowired
	public UserDetailsDaoImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void removeAll(List<UserDetailsModel> users) {
		getHibernateTemplate().deleteAll(users);
	}
	
	@Override
	public List<UserDetailsModel> getAllUserDetailss() {
		return getHibernateTemplate().loadAll(UserDetailsModel.class);
	}
	
	@Override
	public void saveUserDetails(UserDetailsModel user) {
		getHibernateTemplate().save(user);
	}
}
