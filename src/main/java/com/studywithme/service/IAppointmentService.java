package com.studywithme.service;

import com.studywithme.model.Appointment;
import com.studywithme.model.User;
import com.studywithme.paging.Pageable;
import java.util.List;

public interface IAppointmentService {
    public List<Appointment> pagingAppointment(Pageable pageable);
    public Integer totalItem(Pageable pageable);
    public List<Appointment> findByHost(User host);
    public Appointment joinAppointment(User user, String idAppointment);
    public boolean leaveAppointment(User user, String idAppointment);
    public List<Appointment> findByHostCurrent(User host);
    public List<Appointment> findByParticipants(User participant,String maxItem);
    public List<Appointment> findByParticipantCurrent(User participant);
    public Integer countFindByParticipants(User participant);
    public Appointment createAppointment(String dateMeetingStr, String startTimeStr, String endTimeStr,String max, String address, String idAddressType, String idWard, User host);
    public Integer totalItemCurrent(User host);
    public List<Appointment> appointmentCurrent(User host);
    public boolean delete(String id);
    public List<Appointment> findAllAppointmentByRate (User user, Pageable pageable);
    public Integer totalFindAllAppointmentByRate (User user);
    public List<Appointment> findAllAppointment(Pageable pageable);
    public Integer countAll();
}
