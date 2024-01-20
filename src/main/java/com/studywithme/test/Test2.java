package com.studywithme.test;



import com.studywithme.service.impl.AppointmentService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Test2 {
    public static void main(String[] args) {
//        Date d = new Date(System.currentTimeMillis());
//        String date = new SimpleDateFormat("yyyy-MM-dd").format(d);
//        System.out.println(date);
//        try {
//
//            java.util.Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-11");
//            Date d = new Date(d1.getTime());
////            Date d = new Date(System.currentTimeMillis());
//            System.out.println(d);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
        AppointmentService appointmentService = new AppointmentService();
    }
}
