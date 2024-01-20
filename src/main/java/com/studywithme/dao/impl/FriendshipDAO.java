package com.studywithme.dao.impl;

import com.studywithme.dao.IFriendshipDAO;
import com.studywithme.model.Friendship;
import com.studywithme.model.User;
import com.studywithme.paging.Pageable;
import com.studywithme.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class FriendshipDAO extends AbstractDAO<Friendship> implements IFriendshipDAO {
    private static IFriendshipDAO friendshipDAO;
    public static IFriendshipDAO getInstance() {
        if (friendshipDAO == null) {
            friendshipDAO = new FriendshipDAO();
        }
        return friendshipDAO;
    }
    @Override
    public List<Friendship> findAll() {
        String hql = "from Friendship";
        List<Friendship> friendships = query(hql);
        return friendships.isEmpty()?null:friendships;
    }

    @Override
    public Friendship findOne(Integer id) {
        return findId(Friendship.class,id);
    }

    @Override
    public Friendship save(Friendship friendship) {
        return insert(friendship);
    }

    @Override
    public boolean deleteId(Integer id) {
        return delete(findOne(id));
    }


    @Override
    public List<Friendship> listFriend(Integer index,User user) {
        List<Friendship> results = new ArrayList<>();
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                String hql = "from  Friendship f WHERE (f.friend =: user or f.requester=: user) and f.status = 0 order by  f.createdDate asc ";
                Query query = session.createQuery(hql);
                query.setParameter("user",user);
                results = query.setFirstResult(0).setMaxResults(index).getResultList();
                for(int i = 0; i < results.size(); i++){
                    session.get(User.class,results.get(i).getFriend().getId());
                    session.get(User.class,results.get(i).getRequester().getId());
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
    public List<Friendship> pagingFriend(Pageable pageable, User user) {
        List<Friendship> results = new ArrayList<>();
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                StringBuilder hql = new StringBuilder("from Friendship f where (f.friend =: user or f.requester=: user) and f.status = 0");
                if(pageable.getSorter() != null) {
//                    hql.append(" order by f." + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy()+ "");
                }
                Query query = session.createQuery(hql.toString());
                query.setParameter("user",user);
                results = query.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getLimit()).getResultList();
                for(int i = 0; i < results.size(); i++){
                    session.get(User.class,results.get(i).getFriend().getId());
                    session.get(User.class,results.get(i).getRequester().getId());
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
    public Integer countFriend(User user) {
        String hql = "from Friendship f where (f.friend =: user or f.requester=: user) and f.status = 0";
        return count(hql, "user", user);
    }

    @Override
    public List<Friendship> getRequests(User user) {
        List<Friendship> results = new ArrayList<>();
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if(sessionFactory!=null) {
                Session session = sessionFactory.openSession();
                Transaction tr = session.beginTransaction();
                String hql = "from Friendship f where f.friend = :user and f.status = 1";
                Query query = session.createQuery(hql);
                query.setParameter("user",user);
                results = query.getResultList();
                for(int i = 0; i < results.size(); i++){
                    session.get(User.class,results.get(i).getFriend().getId());
                    session.get(User.class,results.get(i).getRequester().getId());
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
    public Friendship getRelationship(User user, User otherUser) {
        String hql = "from Friendship f where (f.friend = :user and f.requester = :otherUser) or (f.friend = :otherUser and f.requester = :user)";
        List<Friendship> friendships = query(hql, "user", user, "otherUser", otherUser);
        return friendships.isEmpty()? null : friendships.get(0);
    }

}
