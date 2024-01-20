package com.studywithme.service.impl;

import com.studywithme.dao.impl.DistrictDAO;
import com.studywithme.dao.impl.WardDAO;
import com.studywithme.model.User;
import com.studywithme.model.Ward;
import com.studywithme.service.IWardService;

import java.sql.Date;

public class WardService implements IWardService {
    private static IWardService wardService;
    public static IWardService getInstance() {
        if (wardService == null) {
            wardService = new WardService();
        }
        return wardService;
    }
    @Override
    public boolean createWard(String ward, String idDistrict, User creator) {
        Ward ward1 = new Ward();
        ward1.setWard(ward);
        ward1.setDistrict(DistrictDAO.getInstance().findOne(Integer.parseInt(idDistrict)));
        ward1.setCreatedBy(creator);
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        Date today = new Date(date.getTime());
        ward1.setCreatedDate(today);
        WardDAO.getInstance().insert(ward1);
        return true;
    }

    @Override
    public boolean deleteWard(String id) {
        return WardDAO.getInstance().deleteWard(WardDAO.getInstance().findOne(Integer.parseInt(id)));
    }
}
