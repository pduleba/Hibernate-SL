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

import com.pduleba.hibernate.model.ProductModel;

@Repository
@Transactional
public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao {

	public static final Logger LOG = LoggerFactory.getLogger(ProductDaoImpl.class);

	@Autowired
	public ProductDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public List<ProductModel> getAllProducts() {
		return getHibernateTemplate().loadAll(ProductModel.class);
	}

	@Override
	public Serializable save(ProductModel product) {
		return getHibernateTemplate().save(product);
	}

	@Override
	public void removeAll(List<ProductModel> products) {
		getHibernateTemplate().deleteAll(products);
	}
}
