package com.studywithme.service;

import com.studywithme.model.Friendship;
import com.studywithme.model.User;
import com.studywithme.paging.Pageable;

import java.util.List;

public interface IFriendshipService {
    public List<Friendship> listFriend(String index, User user);
    public List<Friendship> pagingFriend(Pageable pageable, User user);
    public Integer totalFriend(User user);
    public boolean addFriend(User requester, User friend);
    public Friendship getFriendship(User user, User me);
    public List<Friendship> getRequest(User me);
    public boolean unFriend(String id);
    public boolean reply(String id, String action);
}
