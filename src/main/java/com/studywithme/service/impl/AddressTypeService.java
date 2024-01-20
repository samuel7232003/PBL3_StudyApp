package com.studywithme.service.impl;

import com.studywithme.dao.IAddressTypeDAO;
import com.studywithme.dao.impl.AddressTypeDAO;
import com.studywithme.model.AddressType;
import com.studywithme.service.IAddressTypeService;

import java.util.List;

public class AddressTypeService implements IAddressTypeService {
    private static IAddressTypeService addressTypeService;
    public static IAddressTypeService getInstance() {
        if (addressTypeService == null) {
            addressTypeService = new AddressTypeService();
        }
        return addressTypeService;
    }
    @Override
    public List<AddressType> findAll() {
        return AddressTypeDAO.getInstance().findAll();
    }
}
