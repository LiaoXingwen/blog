package cc.bblog.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import cc.bblog.dao.BaseDao;
import cc.bblog.entity.QueryMapping;

@ComponentScan
@Component("baseDao")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {


	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public Object save(Object object) {
		return this.getHibernateTemplate().save(object);
	}

	@Override
	public boolean update(Object object) {
		if (object==null) {
			return false ; 
		}
		 this.getHibernateTemplate().update(object);
		 return true ;
	}

	@Override
	public boolean saveOrUpdate(Object object) {
		if (object==null) {
			return false ; 
		}
		this.getHibernateTemplate().saveOrUpdate(object);
		return true ;
	}

	@Override
	public Object findById(Object object, Serializable id) {
		if (object==null) {
			return null ; 
		}
		return this.getHibernateTemplate().get(object.getClass(), id);
	}
	

	@Override
	public void delete(Object object) {
		this.getHibernateTemplate().delete(object);
	}
	@Override
	public void execute(String hql) {
		this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {   
			public Integer doInHibernate(Session session)   
					throws HibernateException { 
				return  session.createQuery(hql)
						.executeUpdate();
			}   
		});
	}
	
	
	@Override
	public void delete(List<?> list) {
		this.getHibernateTemplate().deleteAll(list);

	}
	@Override
	public Object query(String hql) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Object>() {   
			public Object doInHibernate(Session session)   
					throws HibernateException { 
				return  session.createQuery(hql)
						.list();
			}   
		});
	}
	@Override
	public List<Object> findByQueryMapping(Object object, QueryMapping queryMapping,
			int firstResult, int maxResults, Order order) {
		return findByJunction(object, queryMapping.getRestrictions(), firstResult, maxResults, order);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByJunction(Object object,  Junction junction,
			int firstResult, int maxResults, Order order) {
		return (List<Object>) this.getHibernateTemplate().execute(new HibernateCallback<List<?>>() {   
			public List<?> doInHibernate(Session session)   
					throws HibernateException { 
				Criteria criteria = session.createCriteria(object.getClass());
				criteria.add(junction);
				criteria.setMaxResults(maxResults);
				criteria.setFirstResult(firstResult);
				criteria.addOrder(order);
				return criteria.list();
			}   
		});
	}
	@Override
	public long count(Object object, Junction junction) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Long>() {   
			public Long doInHibernate(Session session)   
					throws HibernateException { 
				Criteria criteria = session.createCriteria(object.getClass());
				criteria.add(junction);
				criteria.setProjection(Projections.rowCount());  
				return (Long)criteria.uniqueResult();
			}   
		});
	}
	@Override
	public void saveAll(Set<?> set) {
		
		this.getHibernateTemplate().execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session)
					throws HibernateException {
				for (Object o :set) {
					session.save(o);
					System.out.println(o.toString());
				}
				return null;
			}
		});
		
	}
	@Override
	public List executeSQL(String sql) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List>() {

			@Override
			public List doInHibernate(Session session)
					throws HibernateException {
				return session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			}
		});
		
	}

}
