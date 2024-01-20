package com.studywithme.dao;

import com.studywithme.model.District;

import java.util.List;

public interface IDistrictDAO extends GenericDAO<District>, InterfaceDAO<District>{
    public boolean deleteDistrict(District district);
}
