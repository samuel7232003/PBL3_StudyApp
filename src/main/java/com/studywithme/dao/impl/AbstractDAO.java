package com.studywithme.dao.impl;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.studywithme.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.studywithme.dao.GenericDAO;
import com.studywithme.model.School;
import com.studywithme.util.HibernateUtil;

public class AbstractDAO<T> implements GenericDAO<T> {

	private void setParameters(Query query,Object...parameters) {
		for(int i = 0; i < parameters.length; i+=2) {
			Object parameter = parameters[i+1];
			if(parameter instanceof Date) {
				query.setParameter((String) parameters[i], (Date) parameter);
			} else if (parameter instanceof String) {
				query.setParameter((String) parameters[i],(String) parameter);
			} else if (parameter instanceof Integer) {
				query.setParameter((String) parameters[i],(Integer) parameter);
			} else if (parameter instanceof Time) {
				query.setParameter((String) parameters[i],(Time) parameter);
			} else if(parameter instanceof School) {
				query.setParameter((String) parameters[i],(School) parameter);
			} else if(parameter instanceof User) {
				query.setParameter((String) parameters[i],(User) parameter);
			} else if(parameter instanceof java.sql.Date) {
				query.setParameter((String) parameters[i],(java.sql.Date) parameter);
			}
//			else if(parameter instanceof byte[]) {
//				query.setParameter((String) parameters[i],(byte[]) parameter);
//			}
			else if(parameter == null){
				query.setParameter((String) parameters[i], null);
			}
		}
	}
	
	@Override
	public <T> List<T> query(String hql, Object... parameters) {
		List<T> results = new ArrayList();
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				Query query = session.createQuery(hql);
				setParameters(query, parameters);
				results = query.getResultList();
				tr.commit();
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return results;
	}

	@Override
	public T update(T t) {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				/*Query query = session.createQuery(hql);
				setParameters(query, parameters);
				query.executeUpdate();*/
				T t1 = session.merge(t);
				tr.commit();
				session.close();
				return t1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T insert(T t) {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				session.save(t);
				tr.commit();
				session.close();
				return t;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int count(String hql, Object... parameters) {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				Query query = session.createQuery(hql);
				setParameters(query, parameters);
				Long count = (Long) query.stream().count();
				tr.commit();
				session.close();
				return Integer.parseInt(count.toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	@Override
	public T findId(Class<T> clazz,Integer id) {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				T t = session.get(clazz,id);
				tr.commit();
				session.close();
				return t;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(T t) {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				session.delete(t);
				tr.commit();
				session.close();
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	/*@Override
	public <T> List<T> paging(String hql, Integer index, Integer limit,Object...parameters) {
		List<T> results = new ArrayList();
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				Query query = session.createQuery(hql);
				setParameters(query, parameters);
				results = query.setFirstResult((index-1)*limit).setMaxResults(limit).getResultList();
				tr.commit();
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return results;
	}*/
}
