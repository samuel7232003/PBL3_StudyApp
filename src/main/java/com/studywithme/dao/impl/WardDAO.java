package com.studywithme.dao.impl;

import com.studywithme.dao.IWardDAO;
import com.studywithme.model.Address;
import com.studywithme.model.Appointment;
import com.studywithme.model.User;
import com.studywithme.model.Ward;
import com.studywithme.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class WardDAO extends AbstractDAO<Ward> implements IWardDAO {
    private static IWardDAO wardDAO;
    public static IWardDAO getInstance() {
        if (wardDAO == null) {
            wardDAO = new WardDAO();
        }
        return wardDAO;
    }
    @Override
    public List<Ward> findAll() {
        String hql = "from Ward";
        List<Ward> wards = query(hql);
        return wards.isEmpty()?null:wards;
    }

    @Override
    public Ward findOne(Integer id) {
        return findId(Ward.class,id);
    }

    @Override
    public Ward save(Ward ward) {
        return insert(ward);
    }

    @Override
    public boolean deleteId(Integer id) {
        return delete(findOne(id));
    }
    public boolean removeRelationship(Integer id) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                Ward ward = session.get(Ward.class, id);
                for (Address address: ward.getAddresses()){
                    for (Appointment appointment: address.getAppointments())
                    {
                        appointment.setAddress(null);
                    }
                }
                session.update(ward);
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
    public boolean deleteWard(Ward ward) {
        removeRelationship(ward.getId());
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                session.delete(ward);
                tr.commit();
                session.close();
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
