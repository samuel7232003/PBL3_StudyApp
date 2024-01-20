package com.studywithme.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class District extends AbstractModel{

    private String district;
    @OneToMany(mappedBy = "district",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Ward> wards = new HashSet<>();

    public District() {
    }

    public District(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Set<Ward> getWards() {
        return wards;
    }

    public void setWards(Set<Ward> wards) {
        this.wards = wards;
    }
}