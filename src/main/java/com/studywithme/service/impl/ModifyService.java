package com.studywithme.service.impl;

import com.studywithme.dao.IModifyDAO;
import com.studywithme.dao.impl.ModifyDAO;
import com.studywithme.model.Modify;
import com.studywithme.model.User;
import com.studywithme.service.IModifyService;

import java.sql.Date;

public class ModifyService implements IModifyService {
    private static IModifyService modifyService;
    public static IModifyService getInstance(){
        if (modifyService == null) {
            modifyService = new ModifyService();
        }
        return modifyService;
    }
    @Override
    public Modify createModify(User user, User editor, String detail) {
        Modify modify = new Modify();
        modify.setModifyBy(editor);
        modify.setUserModified(user);
        modify.setModifyDate(new Date(System.currentTimeMillis()));
        modify.setDetail(detail);
        return ModifyDAO.getInstance().save(modify);
    }
}
