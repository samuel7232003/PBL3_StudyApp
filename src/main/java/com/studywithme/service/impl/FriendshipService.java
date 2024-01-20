package com.studywithme.service.impl;

import com.studywithme.dao.IFriendshipDAO;
import com.studywithme.dao.impl.FriendshipDAO;
import com.studywithme.model.Friendship;
import com.studywithme.model.User;
import com.studywithme.paging.Pageable;
import com.studywithme.service.IFriendshipService;

import java.sql.Date;
import java.util.List;

public class FriendshipService implements IFriendshipService {
    private static IFriendshipService friendshipService;
    public static IFriendshipService getInstance() {
        if (friendshipService == null) {
            friendshipService = new FriendshipService();
        }
        return friendshipService;
    }
    @Override
    public List<Friendship> listFriend(String index, User user) {
        if(index == null) {
            List<Friendship> friendships = FriendshipDAO.getInstance().listFriend(5,user);
            return friendships.isEmpty()?null:friendships;
        } else {
            List<Friendship> friendships = FriendshipDAO.getInstance().listFriend(Integer.parseInt(index),user);
            return friendships.isEmpty()?null:friendships;
        }
    }

    @Override
    public List<Friendship> pagingFriend(Pageable pageable, User user) {
        return FriendshipDAO.getInstance().pagingFriend(pageable, user);
    }

    @Override
    public Integer totalFriend(User user) {
        return FriendshipDAO.getInstance().countFriend(user);
    }

    @Override
    public boolean addFriend(User requester, User friend) {
        try {
            Friendship friendship = new Friendship();
            friendship.setFriend(friend);
            friendship.setRequester(requester);
            friendship.setStatus(1);
            friendship.setCreatedBy(requester);
            friendship.setCreatedDate(new Date(new java.util.Date(System.currentTimeMillis()).getTime()));
            FriendshipDAO.getInstance().insert(friendship);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Friendship getFriendship(User user, User me) {
        return FriendshipDAO.getInstance().getRelationship(user,me);
    }

    @Override
    public List<Friendship> getRequest(User me) {
        List<Friendship> friendships = FriendshipDAO.getInstance().getRequests(me);
        return friendships.isEmpty()?null:friendships;
    }

    @Override
    public boolean unFriend(String id) {
        return FriendshipDAO.getInstance().delete(FriendshipDAO.getInstance().findOne(Integer.parseInt(id)));
    }

    @Override
    public boolean reply(String id, String action) {
        if (action.equals("agree")) {
            Friendship friendship = FriendshipDAO.getInstance().findOne(Integer.parseInt(id));
            friendship.setStatus(0);
            FriendshipDAO.getInstance().update(friendship);
            return true;
        } else if (action.equals("deny")) {
            Friendship friendship = FriendshipDAO.getInstance().findOne(Integer.parseInt(id));
            FriendshipDAO.getInstance().delete(friendship);
            return true;
        }
        return false;
    }

}
