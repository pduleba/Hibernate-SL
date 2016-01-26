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
import com.pduleba.hibernate.model.constructor.FirstAndLastName;
import com.pduleba.spring.dao.parameters.OwnerParamterValueBean;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<OwnerModel> queryForList() {
		return (List<OwnerModel>) getSession().createQuery("select o from OwnerModel o").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OwnerModel> queryWithJoin() {
		return (List<OwnerModel>) getSession().createQuery("select o from OwnerModel o JOIN FETCH o.cars").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OwnerModel> queryByEnum(OwnerType type) {
		Query query = getSession().createQuery("select o from OwnerModel o where o.type = :type");
		query.setParameter("type", type);
		
		return (List<OwnerModel>) query.list();
	}
	
	@Override
	public Long queryForAggregateFunction() {
		return (Long) getSession().createQuery("select max(o.id) from OwnerModel o").uniqueResult();
	}
	
	@Override
	public List<?> queryWithGroupBy() {
		return (List<?>) getSession().createQuery("select o.firstName, COUNT(c) from OwnerModel o LEFT JOIN o.cars c GROUP BY o.firstName").list();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<OwnerModel> queryWithNumericBinding() {
		Query query = getSession().createQuery("select o from OwnerModel o where o.firstName = :firstName and o.lastName = :lastName");
		query.setParameter("firstName", "Jola");
		query.setParameter("lastName", "J");
		
		return (List<OwnerModel>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OwnerModel> queryWithNameBinding() {
		Query query = getSession().createQuery("select o from OwnerModel o where o.firstName = ? and o.lastName = ?");
		query.setParameter(0, "Jola");
		query.setParameter(1, "J");
		
		return (List<OwnerModel>) query.list();
	}

	@Override
	public OwnerModel queryForSingleObject() {
		Query query = getSession().createQuery("select o from OwnerModel o where o.id = :id");
		query.setParameter("id", Long.valueOf(2));
		
		return (OwnerModel) query.uniqueResult();
	}

	@Override
	public Object queryForSingleField() {
		Query query = getSession().createQuery("select o.firstName from OwnerModel o where o.id = :id");
		query.setParameter("id", Long.valueOf(2));
		
		return query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OwnerModel> queryForArrayOfFields() {
		Query query = getSession().createQuery("select o.firstName, o.lastName, o.type from OwnerModel o where o.id = :id");
		query.setParameter("id", Long.valueOf(2));
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FirstAndLastName> queryWithProjectionAndConstructor() {
		return (List<FirstAndLastName>) getSession()
				.createQuery(
						"select new " + FirstAndLastName.class.getName() + "(o.firstName, o.lastName) from OwnerModel o")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OwnerModel> queryWithOrderBy() {
		return getSession().createQuery("select o from OwnerModel o ORDER BY o.firstName ASC, o.age DESC").list();
	}
	
	@Override
	public List<?> queryWithHaving() {
		return getSession()
				.createQuery(
						"select o.firstName, o.lastName from OwnerModel o LEFT JOIN o.cars c GROUP BY o.firstName, o.lastName HAVING count(c.id) > 2")
				.list();
	}
	
	@Override
	public List<?> executeSelectWithJoin(OwnerParamterValueBean valueBean) {
//		getHibernateTemplate().findByValueBean("", valueBean);
//		return getSession().createQuery("select o.firstName, o.lastName, o.type from OwnerModel o where o.id = :id").list();
		
		LOG.info("/" + getSession().getTenantIdentifier() + "/");
		
		return null;
	}
	
	@Override
	public List<?> executeSelectWithFetchSize(int fetchSize) {
//		getSession().setFetchSize(fetchSize);
//		iterate
//		return getSession().createQuery("select o.firstName, o.lastName, o.type from OwnerModel o where o.id = :id").list();
		return null;
	}
	
}
