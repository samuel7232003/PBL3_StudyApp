package com.studywithme.test;

import com.studywithme.dao.impl.AppointmentDAO;
import com.studywithme.model.Appointment;
import com.studywithme.model.User;
import com.studywithme.service.impl.UserService;

import java.util.List;

public class Test6 {
    public static void main(String[] args) {
        User user = UserService.getInstance().findById(1);
//        List<Appointment> appointments = AppointmentDAO.getInstance().findAllAppointmentByRate(user);
        Integer a = AppointmentDAO.getInstance().countFindAllAppointmentByRate(user);
        System.out.println("????");
    }
}
