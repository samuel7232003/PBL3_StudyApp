package com.studywithme.service;

import com.studywithme.model.User;

public interface IRateService {
    public boolean createRate(String idAppointment, String rate, User user);
}
