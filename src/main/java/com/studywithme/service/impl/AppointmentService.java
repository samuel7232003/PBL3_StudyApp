package com.studywithme.service.impl;

import com.studywithme.dao.impl.AddressDAO;
import com.studywithme.dao.impl.AddressTypeDAO;
import com.studywithme.dao.impl.AppointmentDAO;
import com.studywithme.model.Address;
import com.studywithme.model.Appointment;
import com.studywithme.model.User;
import com.studywithme.model.Ward;
import com.studywithme.paging.Pageable;
import com.studywithme.service.IAppointmentService;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class AppointmentService implements IAppointmentService {
    private static IAppointmentService appointmentService;
    public static IAppointmentService getInstance() {
        if (appointmentService == null) {
            appointmentService = new AppointmentService();
        }
        return appointmentService;
    }

    @Override
    public List<Appointment> pagingAppointment(Pageable pageble) {
        return AppointmentDAO.getInstance().pagingAppointment(pageble);
    }

    @Override
    public Integer totalItem(Pageable pageble) {
        return  AppointmentDAO.getInstance().count(pageble);
    }

    @Override
    public List<Appointment> findByHost(User host) {
        return  AppointmentDAO.getInstance().findByHost(host);
    }

    @Override
    public Appointment joinAppointment(User user, String idAppointment) {
        return AppointmentDAO.getInstance().addParticipant(user, Integer.parseInt(idAppointment));
    }

    @Override
    public boolean leaveAppointment(User user, String idAppointment) {
        return AppointmentDAO.getInstance().removeParticipant(user, Integer.parseInt(idAppointment));
    }

    @Override
    public List<Appointment> findByHostCurrent(User host) {
        return  AppointmentDAO.getInstance().findByHostCurrent(host);
    }

    @Override
    public List<Appointment> findByParticipants(User participant, String maxItem) {
        if (maxItem == null) {
                return  AppointmentDAO.getInstance().findByParticipants(participant,5);
        } else {
            return  AppointmentDAO.getInstance().findByParticipants(participant,Integer.parseInt(maxItem));
        }
    }

    @Override
    public List<Appointment> findByParticipantCurrent(User participant) {
        return  AppointmentDAO.getInstance().findByParticipantCurrent(participant);
    }

    @Override
    public Integer countFindByParticipants(User participant) {
        return  AppointmentDAO.getInstance().countFindByParticipants(participant);
    }

    @Override
    public Appointment createAppointment(String dateMeetingStr, String startTimeStr, String endTimeStr, String max, String address, String idAddressType, String idWard, User host) {
        java.sql.Date dateMeeting = null;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateMeetingStr);
            dateMeeting = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Time startTime = Time.valueOf(LocalTime.parse(startTimeStr));
        Time endTime = Time.valueOf(LocalTime.parse(endTimeStr));
        Address address1 = new Address();
//        addressTypeDAO = new AddressTypeDAO();
        address1.setDetail(address);
        address1.setWard(new Ward(Integer.parseInt(idWard)));
        address1.setCreatedBy(host);
        address1.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));
        address1.setCreatedBy(host);
        address1.setAddressType(AddressTypeDAO.getInstance().findOne(Integer.parseInt(idAddressType)));

        Appointment appointment = new Appointment();
        appointment.setAddress(AddressDAO.getInstance().insert(address1));
        appointment.setStarting_time(startTime);
        appointment.setEnding_time(endTime);
        appointment.setDateMeeting(dateMeeting);
        appointment.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));
        appointment.setHost(host);
        appointment.setCreatedBy(host);
        appointment.setStatus(0);
        appointment.setMaximum(Integer.parseInt(max));
        appointment.setTotalParticipant(1);
        return  AppointmentDAO.getInstance().insert(appointment);
    }

    @Override
    public Integer totalItemCurrent(User host) {
        return  AppointmentDAO.getInstance().countByHostCurrent(host);
    }

    @Override
    public List<Appointment> appointmentCurrent(User host) {
        return  AppointmentDAO.getInstance().findByHostCurrent(host);
    }

    @Override
    public boolean delete(String id) {
        return  AppointmentDAO.getInstance().delete( AppointmentDAO.getInstance().findOne(Integer.parseInt(id)));
    }

    @Override
    public List<Appointment> findAllAppointmentByRate(User user, Pageable pageable) {
        return AppointmentDAO.getInstance().findAllAppointmentByRate(user, pageable);
    }

    @Override
    public Integer totalFindAllAppointmentByRate(User user) {
        return AppointmentDAO.getInstance().countFindAllAppointmentByRate(user);
    }

    @Override
    public List<Appointment> findAllAppointment(Pageable pageable) {
        return AppointmentDAO.getInstance().findAllAppointment(pageable);
    }

    @Override
    public Integer countAll() {
        return AppointmentDAO.getInstance().countAll();
    }
}