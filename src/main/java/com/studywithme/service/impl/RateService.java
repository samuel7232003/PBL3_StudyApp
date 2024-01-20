package com.studywithme.service.impl;

import com.studywithme.dao.impl.AppointmentDAO;
import com.studywithme.dao.impl.RateDAO;
import com.studywithme.dao.impl.UserDAO;
import com.studywithme.model.Rate;
import com.studywithme.model.User;
import com.studywithme.service.IRateService;

import java.util.Date;

public class RateService implements IRateService {
    private static IRateService rateService;
    public static IRateService getInstance() {
        if (rateService == null) {
            rateService = new RateService();
        }
        return rateService;
    }
    @Override
    public boolean createRate(String idAppointment, String rate, User user) {
        String[] rateArr = rate.split("-");
        Rate rateNew = new Rate();
        Date date = new Date(System.currentTimeMillis());
        java.sql.Date today = new java.sql.Date(date.getTime());
        for (int i = 0; i < rateArr.length; i+=2){
            rateNew.setRateBy(user);
            rateNew.setCreatedBy(user);
            rateNew.setAppointment(AppointmentDAO.getInstance().findOne(Integer.parseInt(idAppointment)));
            rateNew.setCreatedDate(today);
            rateNew.setRated(UserDAO.getInstance().findOne(Integer.parseInt(rateArr[i])));
            rateNew.setPoint(Integer.parseInt(rateArr[i+1]));
            RateDAO.getInstance().insert(rateNew);
        }
        return true;
    }
}
