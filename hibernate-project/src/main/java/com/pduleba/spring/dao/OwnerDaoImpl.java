package com.pduleba.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.hibernate.model.OwnerModel;
import com.pduleba.hibernate.model.OwnerType;
import com.pduleba.spring.dao.parameters.OwnerParamterValueBean;

@Repository
@Transactional
public class OwnerDaoImpl extends AbstractDaoSupport<OwnerModel> implements OwnerDao {
	
	public static final Logger LOG = LoggerFactory.getLogger(OwnerDaoImpl.class);
	
	@Autowired
	public OwnerDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, OwnerModel.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OwnerModel> executeSelect() {
		return (List<OwnerModel>) getSession().createQuery("select o from OwnerModel o").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OwnerModel> executeSelectWithJoin() {
		return (List<OwnerModel>) getSession().createQuery("select o from OwnerModel o JOIN FETCH o.cars").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OwnerModel> executeSelectByEnum(OwnerType type) {
		Query query = getSession().createQuery("select o from OwnerModel o where o.type = :type");
		query.setParameter("type", type);
		
		return (List<OwnerModel>) query.list();
	}
	
	@Override
	public Long executeSelectWithFunctionMax() {
		return (Long) getSession().createQuery("select max(o.id) from OwnerModel o").uniqueResult();
	}
	
	@Override
	public List<?> executeSelectWithGroupBy() {
		return (List<?>) getSession().createQuery("select o, COUNT(c) from OwnerModel o LEFT JOIN o.cars c GROUP BY o").list();
	}
	
	@Override
	public List<OwnerModel> executeSelectWithHaving() {
		return null;
	}
	
	@Override
	public List<OwnerModel> executeSelectWithJoin(OwnerParamterValueBean valueBean) {
		return null;
	}
	@Override
	public List<OwnerModel> executeSelectWithFetchSize(int fetchSize) {
		return null;
	}
	
	@Override
	public List<OwnerModel> executeSelectWithOrderBy() {
		return null;
	}
	
	// ------------------------------------------------
	//					Crete DB methods
	// ------------------------------------------------
	@Override
	public int getNumberOfOwners() {
		return DataAccessUtils.intResult(getSession().createQuery("select count(*) from OwnerModel").list());
	}
}
