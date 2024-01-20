package com.studywithme.dao.impl;

import com.studywithme.dao.IDistrictDAO;
import com.studywithme.model.Address;
import com.studywithme.model.Appointment;
import com.studywithme.model.District;
import com.studywithme.model.Ward;
import com.studywithme.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DistrictDAO extends AbstractDAO<District> implements IDistrictDAO {
    private static IDistrictDAO districtDAO;
    public static IDistrictDAO getInstance() {
        if (districtDAO == null) {
            districtDAO = new DistrictDAO();
        }
        return districtDAO;
    }
    @Override
    public List<District> findAll() {
        String hql = "From District";
        List<District> districts = query(hql);
        return districts.isEmpty()? null : districts;
    }

    @Override
    public District findOne(Integer id) {
        return findId(District.class,id);
    }

    @Override
    public District save(District district) {
        return insert(district);
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
                District district = session.get(District.class, id);
                for(Ward ward: district.getWards()) {
                    for (Address address : ward.getAddresses()) {
                        for (Appointment appointment : address.getAppointments()) {
                            appointment.setAddress(null);
                        }
                    }
                }
                session.update(district);
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
    public boolean deleteDistrict(District district) {
        removeRelationship(district.getId());
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                session.delete(district);
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
