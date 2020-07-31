package com.inventory.originssoft.inventoryserver.dao.implHibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.inventory.originssoft.inventoryserver.dao.BaseDao;
import com.inventory.originssoft.inventoryserver.exception.IntegrationDaoException;
import com.inventory.originssoft.inventoryserver.exception.IntegrationException;
import com.inventory.originssoft.inventoryserver.query.Param;
import com.inventory.originssoft.inventoryserver.query.Query;

@Repository
@Transactional
public class BaseDaoHibernateImpl<T> implements BaseDao<T> {

	private static final Logger logger = LoggerFactory.getLogger(BaseDaoHibernateImpl.class);

	@Autowired
	public SessionFactory sessionFactory;
	
	private String modelClass;
	
	public BaseDaoHibernateImpl()
	{}
	
	public BaseDaoHibernateImpl(String cls)
	{
		modelClass = cls;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		try {
		    return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    return sessionFactory.openSession();
		}
	}

	@Override
	@Transactional
	public boolean createPersist(T element) throws IntegrationDaoException {
		Session session = null;
		try {
			session = getSession();
			session.clear();
			session.beginTransaction();
			//session.saveOrUpdate(element);
			session.persist(element);
			session.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			logger.error("Exception ::"+ex.getMessage());
			throw new IntegrationDaoException(new IntegrationException(ex));
		}
		finally{
			if(session != null){
				session.flush();
				session.close();	
			}
		}
	}
	
	
	@Override
	@Transactional
	public boolean create(T element) throws IntegrationDaoException {
		Session session = null;
		try {
			session = getSession();
			session.clear();
			session.beginTransaction();
			session.saveOrUpdate(element);
			//session.persist(element);
			session.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			logger.error("Exception ::"+ex.getMessage());
			throw new IntegrationDaoException(new IntegrationException(ex));
		}
		finally{
			if(session != null){
				session.flush();
				session.close();	
			}
		}
	}
	
	@Override
	@Transactional
	public boolean delete(String id) throws IntegrationDaoException {
		return _delete(modelClass, id);
	}
	
	protected boolean _delete(String type, String id) throws IntegrationDaoException {
		Session session = null;
		try {
			session = getSession();
			session.clear();
			session.beginTransaction();
			session.delete(type, id);
			session.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			logger.error("Exception ::"+ex.getMessage());
			throw new IntegrationDaoException(new IntegrationException(ex));
		}
		finally{
			if(session != null){
				session.flush();
				session.close();	
			}
		}
	}

	@Override
	@Transactional
	public boolean delete(T element) throws IntegrationDaoException {
		Session session = null;
		try {
			session = getSession();
			session.clear();
			session.beginTransaction();
			session.delete(element);
			session.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			logger.error("Exception ::"+ex.getMessage());
			throw new IntegrationDaoException(new IntegrationException(ex));
		}
		finally{
			if(session != null){
				session.flush();
				session.close();	
			}
		}
	}
	
	@Override
	@Transactional
	public List<T> query(Query q) throws IntegrationDaoException {
		return _query(modelClass, q);
	}

	@Transactional
	public T query(String id) throws IntegrationDaoException {
		return _query(modelClass, id);
	}
	
	@Transactional
	public T _query(String type, String id) throws IntegrationDaoException {

		Session session = null;
		
		try {
			session = getSession();
			T t = (T) session.get(type, Integer.parseInt(id));
			session.beginTransaction();
			session.getTransaction().commit();
			return t;
		} catch (Exception ex) {
			logger.error("Exception ::"+ex.getMessage());
			ex.printStackTrace();
			throw new IntegrationDaoException(new IntegrationException(ex));
		}
		finally{
			if(session != null){
				session.flush();
				session.close();	
			}
		}
	}

	public List<T> _query(String cls, Query q) throws IntegrationDaoException {
		
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(cls);
			
			Object[] keys = q.keySet().toArray();
			
			for(int i=0; i<q.size(); i++)
			{
				String key = String.valueOf(keys[i]);
				Param param = q.getParameter(key);
				
				String operator = param.getOperator();
				
				if(operator.equalsIgnoreCase("="))
				{
					criteria.add(Restrictions.eq(key, param.getValue()));
				}
				else if(operator.equalsIgnoreCase("!="))
				{
					criteria.add(Restrictions.not(Restrictions.eq(key, param.getValue())));
				}
		
			}
			session.beginTransaction();
			List<T> list = criteria.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception ex) {
			logger.error("Exception ::"+ex.getMessage());
			throw new IntegrationDaoException(new IntegrationException(ex));
		}
		finally{
			if(session != null){
				session.flush();
				session.close();	
			}
		}		
	}
	
	@Transactional
	public List<T> list(List<Integer> ids) throws IntegrationDaoException {
		List<T> list = new ArrayList<T>();
		
		for(Integer id: ids)
		{
			list.add(query(id.toString()));
		}
		
		return list;
	}

	@Override
	@Transactional
	public List<T> search(Query q) throws IntegrationDaoException {
		Session session = null;
		try {
			session = getSession();
			session.clear();
			Criteria criteria = session.createCriteria(modelClass);
			
			Object[] keys = q.keySet().toArray();
			
			for(int i=0; i<q.size(); i++)
			{
				String key = String.valueOf(keys[i]);
				Param param = q.getParameter(key);	
				String operator = param.getOperator();
				
				if(operator.equalsIgnoreCase("="))
					criteria.add(Restrictions.eq(key, param.getValue()));

				else if(operator.equalsIgnoreCase("=or")){
					int count = StringUtils.countOccurrencesOf((String) param.getValue(), ",");
					String[] values = ((String) param.getValue()).split(",");
					Disjunction or = Restrictions.disjunction();
					for (int j = 0; j < count; j++)
						or.add(Restrictions.like(key, values[j]));
	
					criteria.add(or);
				}
				else if(operator.equalsIgnoreCase("!="))
					criteria.add(Restrictions.not(Restrictions.eq(key, param.getValue())));
				else if(operator.equalsIgnoreCase("like"))
					criteria.add(Restrictions.like(key, param.getValue()));
				else if(operator.equalsIgnoreCase("in"))
					criteria.createAlias(key, "temp"+i).add(Restrictions.in("temp"+i+".id", new Object[]{param.getValue()}));
				else if(operator.equalsIgnoreCase("<=>"))
				{
					String[] limit   = ((String) param.getValue()).split("-");
					String paramLow  = limit[0];
					String paramHigh = limit[1];
					criteria.add(Restrictions.between(key, Integer.parseInt(paramLow), Integer.parseInt(paramHigh)));
				}
				else if(operator.equalsIgnoreCase("like&like"))
				{
					String[] params   = ((String) param.getKey()).split(",");
					String[] values   = ((String) param.getValue()).split(",");
					criteria.add(Restrictions.conjunction()
					.add(Restrictions.like(params[1], values[1]+"%")).add(Restrictions.like(params[0] ,values[0]+"%")));
				}
				else if(operator.equalsIgnoreCase("&like"))
				{
					String[] params   = ((String) param.getKey()).split(",");
					String[] values   = ((String) param.getValue()).split(",");
					Conjunction and = Restrictions.conjunction();
					for (int j = 0; j < params.length; j++)
						and.add(Restrictions.like(params[j], values[j]+"%"));
				}	
				else if(operator.equalsIgnoreCase("&&="))
				{
					String[] params   = ((String) param.getKey()).split(",");
					String[] values   = ((String) param.getValue()).split(",");
					Conjunction and = Restrictions.conjunction();
					for (int j = 0; j < params.length; j++)
						and.add(Restrictions.like(params[j], values[j]+"%"));
				}

			}
			criteria.setFirstResult(q.getStart());
			criteria.setMaxResults(q.getCount());
//			criteria.setProjection(Projections.rowCount()).uniqueResult();
//			criteria.setFetchSize(q.getCount());
//			criteria.setFirstResult((q.getPage()-1)*q.getCount());
//			criteria.setCacheable(true);
			session.beginTransaction();
			
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<T> list = criteria.list();
			session.getTransaction().commit();
//			logger.debug("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSS:"+criteria.uniqueResult());
			return list;
		} catch (Exception ex) {
			logger.error("Exception ::"+ex.getMessage());
			throw new IntegrationDaoException(new IntegrationException(ex));
		}
		finally{
			if(session != null){
				session.flush();
				session.close();	
			}
		}
	}

	@Override
	@Transactional
	public boolean update(T element) throws IntegrationDaoException {
		Session session = null ;
		try {
			session = getSession();
			session.clear();
			session.beginTransaction();
			session.saveOrUpdate(element);
			session.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			logger.error("Exception ::"+ex.getMessage());
			throw new IntegrationDaoException(new IntegrationException(ex));
		}
		finally {
			if(session != null){
				session.flush();
				session.close();	
			}
		}
	}

	@Override
	@Transactional
	public int save(T element) throws IntegrationDaoException {
		Session session = null ;
		try {
			session = getSession();
			session.clear();
			session.beginTransaction();
			int id = (Integer) session.save(element);
			session.getTransaction().commit();
			return (id) ;
		}
		catch (Exception ex) {
			logger.error("Exception ::"+ex.getMessage());
			throw new IntegrationDaoException(new IntegrationException(ex));
		}
		finally{
			if(session != null){
				session.flush();
				session.close();	
			}
		}
	}

	@Override
	public List<T> runQuery(String query) throws IntegrationDaoException {
		Session session = null ;
		try {
			session = getSession();
			SQLQuery sqlQuery = session.createSQLQuery(query);
			
			sqlQuery.addEntity(modelClass);
			
			List<T> list = sqlQuery.list();
			session.beginTransaction();
			session.getTransaction().commit();
			return list;
		}
		catch (Exception ex) {
			logger.error("Exception ::"+ex.getMessage());
			throw new IntegrationDaoException(new IntegrationException(ex));
		}
		finally{
			if(session != null){
				session.flush();
				session.close();	
			}
		}
	}

	@Override
	public int getTotalMatchResults(Query q) throws IntegrationDaoException {

		Session session = null;
		try {
			session = getSession();
			session.clear();
			Criteria criteria = session.createCriteria(modelClass);
			
			Object[] keys = q.keySet().toArray();
			
			for(int i=0; i<q.size(); i++)
			{
				String key = String.valueOf(keys[i]);
				Param param = q.getParameter(key);	
				String operator = param.getOperator();
				
				if(operator.equalsIgnoreCase("="))
					criteria.add(Restrictions.eq(key, param.getValue()));

				else if(operator.equalsIgnoreCase("=or")){
					int count = StringUtils.countOccurrencesOf((String) param.getValue(), ",");
					String[] values = ((String) param.getValue()).split(",");
					Disjunction or = Restrictions.disjunction();
					for (int j = 0; j < count; j++)
						or.add(Restrictions.like(key, values[j]));
	
					criteria.add(or);
				}
				else if(operator.equalsIgnoreCase("!="))
					criteria.add(Restrictions.not(Restrictions.eq(key, param.getValue())));
				else if(operator.equalsIgnoreCase("like"))
					criteria.add(Restrictions.like(key, param.getValue()));
				else if(operator.equalsIgnoreCase("in"))
					criteria.createAlias(key, "temp"+i).add(Restrictions.in("temp"+i+".id", new Object[]{param.getValue()}));
				else if(operator.equalsIgnoreCase("<=>"))
				{
					String[] limit   = ((String) param.getValue()).split("-");
					String paramLow  = limit[0];
					String paramHigh = limit[1];
					criteria.add(Restrictions.between(key, Integer.parseInt(paramLow), Integer.parseInt(paramHigh)));
				}
				else if(operator.equalsIgnoreCase("like&like"))
				{
					String[] params   = ((String) param.getKey()).split(",");
					String[] values   = ((String) param.getValue()).split(",");
					criteria.add(Restrictions.conjunction()
					.add(Restrictions.like(params[1], values[1]+"%")).add(Restrictions.like(params[0] ,values[0]+"%")));
				}
				else if(operator.equalsIgnoreCase("&like"))
				{
					String[] params   = ((String) param.getKey()).split(",");
					String[] values   = ((String) param.getValue()).split(",");
					Conjunction and = Restrictions.conjunction();
					for (int j = 0; j < params.length; j++)
						and.add(Restrictions.like(params[j], values[j]+"%"));
				}	
				else if(operator.equalsIgnoreCase("&&="))
				{
					String[] params   = ((String) param.getKey()).split(",");
					String[] values   = ((String) param.getValue()).split(",");
					Conjunction and = Restrictions.conjunction();
					for (int j = 0; j < params.length; j++)
						and.add(Restrictions.like(params[j], values[j]+"%"));
				}

			}
			criteria.setProjection(Projections.rowCount()).uniqueResult();
			session.beginTransaction();
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			session.getTransaction().commit();
			logger.debug("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSS:"+criteria.uniqueResult());
			return 100;
		} catch (Exception ex) {
			logger.error("Exception ::"+ex.getMessage());
			throw new IntegrationDaoException(new IntegrationException(ex));
		}
		finally{
			if(session != null){
				session.flush();
				session.close();	
			}
		}
	}

	@Override
	public int getStats(String q) throws IntegrationDaoException {
		Session session = null ;
		try {
			session = getSession();			
			int count = ((Long)session.createQuery(q).setMaxResults(1).uniqueResult()).intValue();
			session.beginTransaction();
			session.getTransaction().commit();
			return count;
		}
		catch (Exception ex) {
			logger.error("Exception ::"+ex.getMessage());
			throw new IntegrationDaoException(new IntegrationException(ex));
		}
		finally{
			if(session != null){
				session.flush();
				session.close();	
			}
		}

	}
}