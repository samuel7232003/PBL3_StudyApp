package com.studywithme.dao;

import com.studywithme.model.Friendship;
import com.studywithme.model.User;
import com.studywithme.paging.Pageable;

import java.util.List;

public interface IFriendshipDAO extends GenericDAO<Friendship>, InterfaceDAO<Friendship> {
    public List<Friendship> listFriend(Integer index, User user);
    public List<Friendship> pagingFriend(Pageable pageable, User user);
    public Integer countFriend(User user);
    public List<Friendship> getRequests(User user);
    public Friendship getRelationship(User user, User otherUser);
}
