package com.studywithme.dao.impl;

import com.studywithme.dao.IAppointmentDAO;
import com.studywithme.model.Address;
import com.studywithme.model.Appointment;
import com.studywithme.model.Rate;
import com.studywithme.model.User;
import com.studywithme.paging.Pageable;
import com.studywithme.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentDAO extends AbstractDAO<Appointment> implements IAppointmentDAO {
    private static IAppointmentDAO appointmentDAO;
    public static IAppointmentDAO getInstance(){
        if(appointmentDAO == null) {
            appointmentDAO = new AppointmentDAO();
        }
        return appointmentDAO;
    }
    @Override
    public List<Appointment> findAll() {
        String hql = "FROM Appointment";
        List<Appointment> appointments = query(hql);
        return appointments.isEmpty()? null : appointments;
    }

    @Override
    public Appointment findOne(Integer id) {
        return findId(Appointment.class,id);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return insert(appointment);
    }

    @Override
    public boolean deleteId(Integer id) {
        return delete(findOne(id));
    }


    @Override
    public List<Appointment> pagingAppointment(Pageable pageble) {
        List<Appointment> results = new ArrayList<>();
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                StringBuilder hql = new StringBuilder("from Appointment a");
                String dateMeeting = pageble.getSorter().getDateMeeting();
                if(pageble.getSorter() != null){
                    if(dateMeeting != null){
                        hql.append(" where a.dateMeeting = :dateMeeting");
                    } else {
                        hql.append(" where a.dateMeeting > :today or (a.dateMeeting = :today and a.ending_time > :now)");
                    }
                    hql.append(" order by a." + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy()+ "");
                }
                Query query = session.createQuery(hql.toString());
                if(dateMeeting != null){
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(pageble.getSorter().getDateMeeting());
                    java.sql.Date dateSQL = new java.sql.Date(date.getTime());
                    query.setParameter("dateMeeting",dateSQL);
                } else {
                    Date date = new Date(System.currentTimeMillis());
                    java.sql.Date dateSQL = new java.sql.Date(date.getTime());
                    query.setParameter("today",dateSQL);
                    query.setParameter("now",Time.valueOf(LocalTime.now()));
                }
                results = query.setFirstResult(pageble.getOffset()).setMaxResults(pageble.getLimit()).getResultList();
                for(int i = 0; i < results.size(); i++){
                    session.get(User.class, results.get(i).getHost().getId());
                    for (User user: results.get(i).getParticipants()) {
                        session.get(User.class, user.getId());
                    }
                }
                tr.commit();
                session.close();
                return results;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public Integer count(Pageable pageble) {
        StringBuilder hql = new StringBuilder("from Appointment a where");
        String dateMeeting = pageble.getSorter().getDateMeeting();
        try {
            if (dateMeeting != null) {
                hql.append(" a.dateMeeting = :dateMeeting");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(pageble.getSorter().getDateMeeting());
                java.sql.Date today = new java.sql.Date(date.getTime());
                return count(hql.toString(),"dateMeeting",today);
            } else {
                hql.append(" a.dateMeeting > :today or (a.dateMeeting = :today and a.ending_time > :now)");
                Date date = new Date(System.currentTimeMillis());
                java.sql.Date today = new java.sql.Date(date.getTime());
                return count(hql.toString(),"today", today, "now", Time.valueOf(LocalTime.now()));
            }
        }catch (ParseException e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Integer countByHostCurrent(User host) {
        String hql = "from Appointment a where a.host = :host and (a.dateMeeting > :today or (a.dateMeeting = :today and a.ending_time > :now))";
        Date date = new Date(System.currentTimeMillis());
        java.sql.Date today = new java.sql.Date(date.getTime());
        return count(hql, "today", today, "now", Time.valueOf(LocalTime.now()), "host", host);
    }

    @Override
    public List<Appointment> findByHost(User host) {
        String hql = "from Appointment a where a.host = :host";
        List<Appointment> appointments = query(hql,"host",host);
        return appointments.isEmpty()? null: appointments;
    }

    @Override
    public List<Appointment> findByHostCurrent(User host) {
        String hql = "from Appointment a where a.host = :host and (a.dateMeeting > :today or (a.dateMeeting = :today and a.ending_time > :now))";
        Date date = new Date(System.currentTimeMillis());
        java.sql.Date today = new java.sql.Date(date.getTime());
        List<Appointment> appointments = query(hql,"today", today, "host", host, "now", Time.valueOf(LocalTime.now()));
        return appointments.isEmpty() ? null: appointments;
    }

    @Override
    public List<Appointment> findByParticipantCurrent(User participant) {
        List<Appointment> results = new ArrayList<>();
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                String hql = "from Appointment a left join a.participants p where (a.dateMeeting > :today or (a.dateMeeting = :today and a.ending_time > :now)) and (p = :participant) order by a.dateMeeting desc";
                Query query = session.createQuery(hql);
                Date date = new Date(System.currentTimeMillis());
                java.sql.Date today = new java.sql.Date(date.getTime());
                query.setParameter("today", today);
                query.setParameter("now",Time.valueOf(LocalTime.now()));
                query.setParameter("participant",participant);
                results = query.setFirstResult(0).getResultList();
                for(int i = 0; i < results.size(); i++){
                    session.get(User.class,results.get(i).getHost().getId());
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
    public List<Appointment> findByParticipants(User participant,Integer maxItem) {
        List<Appointment> results = new ArrayList<>();
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                String hql = "from Appointment a left join a.participants p where (p = :participant or (a.host = :participant and a.participants is not empty)) group by a.id order by a.dateMeeting desc";
                Query query = session.createQuery(hql);
//                query.setParameter("today",new Time(System.currentTimeMillis()));
                query.setParameter("participant",participant);
                results = query.setFirstResult(0).setMaxResults(maxItem).getResultList();
                for(int i = 0; i < results.size(); i++){
                    session.get(User.class,results.get(i).getHost().getId());
                    for (User u : results.get(i).getParticipants()) {
                        session.get(User.class,u.getId());
                    }
                    for (Rate r: results.get(i).getRates()) {
                        session.get(Rate.class,r.getId());
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
    public Integer countFindByParticipants(User participant) {
        String hql = "from Appointment a left join a.participants p where (p = :participant or (a.host = :participant and a.participants is not empty)) group by a.id";
        return count(hql, "participant", participant);
    }

    @Override
    public Appointment addParticipant(User participant, Integer id) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                Appointment appointment = session.get(Appointment.class, id);
                appointment.addParticipant(participant);
                session.update(appointment);
                tr.commit();
                session.close();
                return appointment;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean removeParticipant(User participant, Integer id) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                Appointment appointment = session.get(Appointment.class, id);
                User user = session.get(User.class, participant.getId());
                appointment.removeParticipant(user);
                session.update(appointment);
                tr.commit();
                session.close();
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Appointment> findAllAppointmentByRate(User user, Pageable pageable) {
        List<Appointment> results = new ArrayList<>();
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                StringBuilder hql = new StringBuilder("from Appointment a");
                hql.append(" left join a.participants p where ((a.host = :user and a.participants is not empty ) or p = :user) and (a.dateMeeting < :today or (a.dateMeeting = :today and a.ending_time < :now))");
//                String hql = "from Appointment a left join a.participants p where ((a.host = :user and a.participants is not empty ) or p = :user) and (a.dateMeeting < :today or (a.dateMeeting = :today and a.ending_time < :now)) except from Appointment a inner join a.rates r where r.rateBy = :user";
                if (pageable.getSorter() != null) {
                    hql.append(" order by a." + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy());
                }
                hql.append(" except from Appointment a inner join a.rates r where r.rateBy = :user");
                Query query = session.createQuery(hql.toString());
                Date date = new Date(System.currentTimeMillis());
                java.sql.Date today = new java.sql.Date(date.getTime());
                query.setParameter("today", today);
                query.setParameter("now",Time.valueOf(LocalTime.now()));
                query.setParameter("user",user);
                results = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getLimit()).getResultList();
                for(int i = 0; i < results.size(); i++){
                    session.get(User.class,results.get(i).getHost().getId());
                    for (User u : results.get(i).getParticipants()) {
                        session.get(User.class,u.getId());
                    }
                    for (Rate r: results.get(i).getRates()) {
                        session.get(Rate.class,r.getId());
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
    public Integer countFindAllAppointmentByRate(User user) {
        String hql = "from Appointment a left join a.participants p where ((a.host = :user and a.participants is not empty ) or p = :user) and (a.dateMeeting < :today or (a.dateMeeting = :today and a.ending_time < :now)) except from Appointment a inner join a.rates r where r.rateBy = :user";
        Date date = new Date(System.currentTimeMillis());
        java.sql.Date today = new java.sql.Date(date.getTime());
        return count(hql, "user", user, "today", today, "now", Time.valueOf(LocalTime.now()));
    }

    @Override
    public List<Appointment> findAllAppointment(Pageable pageable) {
        List<Appointment> results = new ArrayList<>();
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                StringBuilder hql = new StringBuilder("from Appointment a");
                String dateMeeting = pageable.getSorter().getDateMeeting();
                if(pageable.getSorter() != null){
                    hql.append(" order by a." + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy()+ "");
                }
                Query query = session.createQuery(hql.toString());
                results = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getLimit()).getResultList();
                for(int i = 0; i < results.size(); i++){
                    session.get(User.class, results.get(i).getHost().getId());
                    for (User user: results.get(i).getParticipants()) {
                        session.get(User.class, user.getId());
                    }
                }
                tr.commit();
                session.close();
                return results;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public Integer countAll() {
        String hql = "FROM Appointment";
        return count(hql);
    }

}
