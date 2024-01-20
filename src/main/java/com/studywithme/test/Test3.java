package com.studywithme.test;

import com.studywithme.dao.impl.Test1DAO;
import com.studywithme.dao.impl.Test2DAO;
import com.studywithme.dao.impl.UserDAO;
import com.studywithme.model.Friendship;
import com.studywithme.model.Test1;
import com.studywithme.model.Test2;
import com.studywithme.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Test3 {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                Test1 t1 = session.get(Test1.class,1);
//                Test2 t2 = session.get(Test2.class,1);
                for (Test2 t2: t1.getTest2s()) {
                    t1.removeTest2(t2);
                }
//                session.persist(t1);
////                tr.commit();
                session.delete(t1);
                tr.commit();
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
