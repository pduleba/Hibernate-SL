package com.pduleba.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.hibernate.model.OwnerModel;

@Repository
@Transactional
public class OwnerDaoImpl extends HibernateDaoSupport implements OwnerDao {
	
	public static final Logger LOG = LoggerFactory.getLogger(OwnerDaoImpl.class);
	
	@Autowired
	public OwnerDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	// ------------------------------------------------
	//					Crete DB methods
	// ------------------------------------------------

	public void create(OwnerModel enity) {
		getHibernateTemplate().save(enity);
	}
	
	public void createAll(List<OwnerModel> entities) {
		for(OwnerModel entity : entities) {
			create(entity);
		}
	}

	public OwnerModel read(long entityId) {
		return getHibernateTemplate().get(OwnerModel.class, entityId);
	}

	public void update(OwnerModel enity) {
		HibernateTemplate template = getHibernateTemplate();
		template.update(template.contains(enity) ? enity : template.merge(enity));
	}

	public void delete(OwnerModel enity) {
		HibernateTemplate template = getHibernateTemplate();
		template.delete(template.contains(enity) ? enity : template.merge(enity));
	}

	@Override
	public int getNumberOfOwners() {
		return DataAccessUtils.intResult(getHibernateTemplate().find("select count(*) from OwnerModel"));
	}
	
	@Override
	public List<OwnerModel> queryForList(String firstName) {
		OwnerModel om = new OwnerModel();
		om.setFirstName(firstName);
		
		return getHibernateTemplate().findByExample(om);
	}
}
