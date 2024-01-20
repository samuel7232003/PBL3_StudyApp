package com.studywithme.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class AddressType extends AbstractModel{
    private String type;
    @OneToMany(mappedBy = "addressType", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Address>  addresses = new HashSet<>();

    public AddressType() {
    }

    public AddressType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public void removeAddress(Address address){
        address.setAddressType(null);
    }
}
