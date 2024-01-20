package com.studywithme.test;

import com.studywithme.model.Appointment;
import com.studywithme.model.User;
import com.studywithme.service.IAppointmentService;
import com.studywithme.service.IUserService;
import com.studywithme.service.impl.AppointmentService;
import com.studywithme.service.impl.UserService;

import java.util.List;

public class Test5 {
    public static void main(String[] args) {
        IUserService userService = new UserService();
        IAppointmentService appointmentService = new AppointmentService();
        User user = userService.findById(2);
        List<Appointment> appointments = appointmentService.findByParticipantCurrent(user);
        System.out.println("123");
    }
}