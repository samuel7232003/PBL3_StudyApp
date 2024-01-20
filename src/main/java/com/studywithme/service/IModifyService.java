package com.studywithme.service;

import com.studywithme.model.Modify;
import com.studywithme.model.User;

public interface IModifyService {
    public Modify createModify(User user, User editor, String detail);
}
