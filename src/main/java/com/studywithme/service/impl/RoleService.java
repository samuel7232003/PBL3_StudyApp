package com.studywithme.service.impl;

import com.studywithme.dao.impl.RoleDAO;
import com.studywithme.model.Role;
import com.studywithme.service.IRoleService;

import java.util.List;

public class RoleService implements IRoleService {
    private static IRoleService roleService;
    public static IRoleService getInstance(){
        if(roleService == null) {
            roleService = new RoleService();
        }
        return roleService;
    }
    @Override
    public List<Role> findAll() {
        return RoleDAO.getInstance().findAll();
    }
}
