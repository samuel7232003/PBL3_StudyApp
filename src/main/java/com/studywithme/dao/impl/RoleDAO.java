package com.studywithme.dao.impl;

import com.studywithme.dao.IRoleDAO;
import com.studywithme.model.Role;

import java.util.List;

public class RoleDAO extends AbstractDAO<Role> implements IRoleDAO {
    private static IRoleDAO roleDAO;
    public static IRoleDAO getInstance() {
        if (roleDAO == null) {
            roleDAO = new RoleDAO();
        }
        return roleDAO;
    }
    @Override
    public List<Role> findAll() {
        String hql = "from Role";
        List<Role> roles = query(hql);
        return roles.isEmpty()?null:roles;
    }

    @Override
    public Role findOne(Integer id) {
        return findId(Role.class,id);
    }

    @Override
    public Role save(Role role) {
        return insert(role);
    }

    @Override
    public boolean deleteId(Integer id) {
        return delete(findOne(id));
    }


    @Override
    public Role findByCode(String code) {
        String hql = "from Role r where r.code = :code";
        List<Role> roles = query(hql, "code", code);
        return roles.isEmpty()?null:roles.get(0);
    }
}
