package com.studywithme.dao;

import com.studywithme.model.Ward;

public interface IWardDAO extends GenericDAO<Ward>, InterfaceDAO<Ward> {
    public boolean deleteWard(Ward ward);
}
