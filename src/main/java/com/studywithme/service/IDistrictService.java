package com.studywithme.service;

import com.studywithme.model.District;

import java.util.List;

public interface IDistrictService {
    public List<District> findAll();
    public boolean createDistrict(String district);
    public boolean deleteDistrict(String idDistrict);
}
