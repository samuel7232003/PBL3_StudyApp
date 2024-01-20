package com.studywithme.dao;

import com.studywithme.model.Role;

public interface IRoleDAO extends GenericDAO<Role>, InterfaceDAO<Role> {
    public Role findByCode(String code);
}
