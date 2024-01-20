	package com.studywithme.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.studywithme.dao.IUserDAO;
import com.studywithme.model.Appointment;
import com.studywithme.model.School;
import com.studywithme.model.User;
import com.studywithme.paging.Pageable;
import com.studywithme.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {
	private static IUserDAO userDAO;
	public static IUserDAO getInstance() {
		if (userDAO == null) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}

	@Override
	public User save(User user) {
		return insert(user);
	}

	@Override
	public boolean deleteId(Integer id) {
		return delete(findOne(id));
	}


	@Override
	public List<User> findAll() {
		String hql = "from user";
		List<User> users = query(hql);
		return users.isEmpty() ? null : users;
	}

	@Override
	public User findOne(Integer id) {
		String hql = "FROM User u Where u.id = :id";
		List<User> user = query(hql,"id", id);
		return user.isEmpty() ? null : user.get(0);
	}


	@Override
	public int getTotalItem() {
		String hql = "SELECT COUNT(*) FROM User";
		return count(hql);
	}

	@Override
	public User findByEmailAndPasswordAndStatus(String email, String password) {
		String hql = "from User u where u.email =:email and u.password =:password";
		List<User> user = query(hql, "email", email, "password", password);
		return user.isEmpty() ? null : user.get(0);
	}

	@Override
	public User findById(Integer id) {
		List<User> results = new ArrayList();
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				String hql = "from User u where u.id = :id";
				Query query = session.createQuery(hql);
				query.setParameter("id",id);
				results = query.getResultList();
				if(results.get(0).getSchool()!=null) {
					session.get(School.class, results.get(0).getSchool().getId());
				}
				tr.commit();
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return results.isEmpty()?null:results.get(0);
	}

	@Override
	public List<User> findAllUser(Pageable pageable) {
		List<User> results = new ArrayList();
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
//				String hql = "from User u where u.role.code = 'USER'";
				StringBuilder hql =  new StringBuilder("from User u where u.role.code = 'USER'");
				if (pageable.getSorter() != null) {
					hql.append(" order by u." + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy());
				}
				Query query = session.createQuery(hql.toString());
				results = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getLimit()).getResultList();
				for (User user: results) {
					if (user.getSchool() != null) {
						session.get(School.class,user.getSchool().getId());
					}
				}
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
	public Integer countFindAllUser() {
		String hql = "from User u where u.role.code = 'USER'";
		return count(hql);
	}

	@Override
	public List<User> findAllAdmin(Pageable pageable) {
		List<User> results = new ArrayList();
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
//				String hql = "from User u where u.role.code = 'USER'";
				StringBuilder hql =  new StringBuilder("from User u where u.role.code = 'ADMIN'");
				if (pageable.getSorter() != null) {
					hql.append(" order by u." + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy());
				}
				Query query = session.createQuery(hql.toString());
				results = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getLimit()).getResultList();
				for (User user: results) {
					if (user.getSchool() != null) {
						session.get(School.class,user.getSchool().getId());
					}
				}
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
	public Integer countFindAllAdmin() {
		String hql = "from User u where u.role.code = 'ADMIN'";
		return count(hql);
	}

	public boolean removeRelationship(Integer id) {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				List<Appointment> result = new ArrayList<>();
				User user = session.get(User.class, id);
				String hql = "from Appointment a left join a.participants p where p = :user";
				Query query = session.createQuery(hql);
				query.setParameter("user", user);
				result = query.getResultList();
				for (Appointment appointment: result) {
					appointment.removeParticipant(user);
				}
				for (Appointment appointment: user.getAppointmentsOf()){
					session.get(Appointment.class, appointment.getId());
				}
				session.update(user);
				tr.commit();
				session.close();
				for (Appointment appointment: user.getAppointmentsOf()){
					AppointmentDAO.getInstance().delete(appointment);
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean deleteUser(User user) {
		removeRelationship(user.getId());
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				Transaction tr = session.beginTransaction();
				session.delete(user);
				tr.commit();
				session.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public User findByEmail(String email) {
		String hql = "FROM User u Where u.email = :email";
		List<User> user = query(hql,"email", email);
		return user.isEmpty() ? null : user.get(0);
	}
}
