package com.studywithme.test;

import com.studywithme.dao.impl.UserDAO;
import com.studywithme.model.User;
import com.studywithme.paging.PageRequest;
import com.studywithme.paging.Pageable;

import java.util.List;

public class Test7 {
    public static void main(String[] args) {
        Pageable pageable = new PageRequest();
        List<User> users = UserDAO.getInstance().findAllUser(pageable);
        System.out.println("???");
    }
}
