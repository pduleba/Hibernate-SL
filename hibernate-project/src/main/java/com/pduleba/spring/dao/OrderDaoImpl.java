package com.pduleba.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.pduleba.hibernate.model.OrderModel;

@Repository
@Transactional
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	@Autowired
	public OrderDaoImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void removeAll(List<OrderModel> orders) {
		getHibernateTemplate().deleteAll(orders);
	}
	
	@Override
	public List<OrderModel> getAllOrders() {
		return getHibernateTemplate().loadAll(OrderModel.class);
	}
	
	@Override
	public void saveOrder(OrderModel order) {
		getHibernateTemplate().save(order);
	}
}
