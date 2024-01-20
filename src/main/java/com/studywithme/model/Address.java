package com.studywithme.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Address extends AbstractModel {

    private String detail;
    @OneToMany(mappedBy = "addressModified",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Modify> modifiedBy = new HashSet<>();
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ward")
    private Ward ward;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_type_address")
    private AddressType addressType;
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Report> reports = new HashSet<>();

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    public Address() {
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Set<Modify> getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Set<Modify> modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addModify(Modify modify){
        modifiedBy.add(modify);
    }

    public void removeAppointment(Appointment appointment){
        appointment.setAddress(null);
    }
}
