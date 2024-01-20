package com.studywithme.dao.impl;

import com.studywithme.dao.IAddressDAO;
import com.studywithme.model.Address;

import java.util.List;

public class AddressDAO extends AbstractDAO<Address> implements IAddressDAO {
    private static IAddressDAO addressDAO;
    public static IAddressDAO getInstance(){
        if (addressDAO == null) {
            addressDAO = new AddressDAO();
        }
        return addressDAO;
    }
    @Override
    public List<Address> findAll() {
        String hql = "from Address";
        List<Address> addresses = query(hql);
        return addresses.isEmpty() ? null : addresses;
    }

    @Override
    public Address findOne(Integer id) {
        return findId(Address.class,id);
    }

    @Override
    public Address save(Address address) {
        return insert(address);
    }

    @Override
    public boolean deleteId(Integer id) {
        return delete(findOne(id));
    }

}
