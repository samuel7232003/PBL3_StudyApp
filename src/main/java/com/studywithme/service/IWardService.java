package com.studywithme.service;

import com.studywithme.model.User;

public interface IWardService {
    public boolean createWard(String ward, String idDistrict, User created);
    public boolean deleteWard(String id);
}
