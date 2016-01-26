package com.pduleba.spring.dao;

import static com.pduleba.hibernate.model.CarModel.PROPERTY_NAME;
import static com.pduleba.hibernate.model.OwnerModel.PROPERTY_CARS;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.hibernate.model.OwnerModel;

@Repository
@Transactional
public class OwnerDaoImpl extends AbstractDaoSupport<OwnerModel> implements OwnerDao {
	
	public static final Logger LOG = LoggerFactory.getLogger(OwnerDaoImpl.class);
	
	@Override
	public int getNumberOfOwners() {
		return DataAccessUtils.intResult(getSession().createQuery("select count(*) from OwnerModel").list());
	}

	// ------------------------------------------------
	//					Crete DB methods
	// ------------------------------------------------
	
	@Autowired
	public OwnerDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, OwnerModel.class);
	}

	@Override
	public List<?> queryForList(String carName) {
		Criteria criteria = getSession().createCriteria(OwnerModel.class);
		criteria.createAlias(PROPERTY_CARS, "c");
		criteria.add(Restrictions.eq(MessageFormat.format("c.{0}", PROPERTY_NAME), carName));
		
		return criteria.list();
	}
}
