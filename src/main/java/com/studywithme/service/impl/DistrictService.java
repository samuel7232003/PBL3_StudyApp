package com.studywithme.service.impl;

import com.studywithme.dao.IDistrictDAO;
import com.studywithme.dao.impl.DistrictDAO;
import com.studywithme.model.District;
import com.studywithme.service.IDistrictService;

import java.util.List;

public class DistrictService implements IDistrictService {
    private static IDistrictService districtService;
    public static IDistrictService getInstance() {
        if (districtService == null) {
            districtService = new DistrictService();
        }
        return districtService;
    }
    @Override
    public List<District> findAll() {
        return DistrictDAO.getInstance().findAll();
    }

    @Override
    public boolean createDistrict(String district) {
        District district1 = new District();
        district1.setDistrict(district);
        DistrictDAO.getInstance().insert(district1);
        return true;
    }

    @Override
    public boolean deleteDistrict(String idDistrict) {
        return DistrictDAO.getInstance().deleteDistrict(DistrictDAO.getInstance().findOne(Integer.parseInt(idDistrict)));
    }
}
