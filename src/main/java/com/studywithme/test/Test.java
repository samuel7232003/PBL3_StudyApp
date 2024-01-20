package com.studywithme.test;

import com.studywithme.model.Appointment;
import com.studywithme.model.User;
import com.studywithme.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Test {
	public static void main(String[] args){
		List<Appointment> appointments = Test.load();
		Test.load1();
		for (int i = 1; i<=appointments.size(); i++){
			System.out.println(appointments.get(i-1).getHost().getEmail());
		}
	}

	public static List<Appointment> load(){
		List<Appointment> results = new ArrayList();
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				String hql = "from Appointment a order by a.createdDate asc ";
				Query query = session.createQuery(hql);
				results = query.setFirstResult(0).setMaxResults(6).getResultList();
//				results = Test.loadSet(session, query.setFirstResult(0).setMaxResults(6).getResultList());
//				for(int i =1; i<=results.size();i++){
//					session.get(User.class,results.get(i-1).getHost().getId());
//				}
//				Test.loadSet(session,results);
//				System.out.println(results.size());
				tr.commit();
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	public static void load1(){
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				session.get(User.class,1);
				session.get(User.class,2);
				tr.commit();
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void loadSet(Session session, List<Appointment> appointments){
		for (int i = 1; i<=appointments.size();i++) {
			session.get(User.class,appointments.get(i-1).getHost().getId());
		}
	}
}
