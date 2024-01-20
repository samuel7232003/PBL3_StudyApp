package com.studywithme.service.impl;

import com.studywithme.service.IAddressService;

public class AddressService implements IAddressService {
    private static IAddressService addressService;
    public static IAddressService getInstance(){
        if (addressService == null) {
            addressService = new AddressService();
        }
        return addressService;
    }
}
