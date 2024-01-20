package com.studywithme.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class User extends AbstractModel {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    private Date dateOfBirth;
    private Integer gender;
    private Integer status;

    @Column(length = Integer.MAX_VALUE)
    private String avatar;
    @Column(length = Integer.MAX_VALUE)
    private String background;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToMany(mappedBy = "participants", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Appointment> listAppointmentsJoin = new HashSet<>();
    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Appointment> appointmentsOf = new HashSet<>();

    @OneToMany( mappedBy = "friend", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Friendship> friends = new HashSet<>();

    @OneToMany( mappedBy = "requester", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Friendship> friendRequests = new HashSet<>();

    @OneToMany(mappedBy = "rateBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Rate> rateBy = new HashSet<>();

    @OneToMany(mappedBy = "rated", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Rate> rated = new HashSet<>();
    private float toltalRate;

    @OneToMany(mappedBy = "modifyBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Modify> modifies = new HashSet<>();

    @OneToMany(mappedBy = "userModified", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Modify> modifiedBy = new HashSet<>();

    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Report> reports = new HashSet<>();

    public User() {
    }


    public User(Integer id, String email, String password, String firstName, String lastName, Date dateOfBirth,
                Integer gender, School school) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.school = school;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
                + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", sex=" + gender + ", dateRegister="
                + "]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Set<Appointment> getListAppointmentsJoin() {
        return listAppointmentsJoin;
    }

    public void setListAppointmentsJoin(Set<Appointment> listAppointmentsJoin) {
        this.listAppointmentsJoin = listAppointmentsJoin;
    }

    public Set<Appointment> getAppointmentsOf() {
        return appointmentsOf;
    }

    public void setAppointmentsOf(Set<Appointment> appointmentsOf) {
        this.appointmentsOf = appointmentsOf;
    }

    public Set<Friendship> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friendship> friends) {
        this.friends = friends;
    }

    public Set<Friendship> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(Set<Friendship> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public float getToltalRate() {
        return toltalRate;
    }

    public void setToltalRate(float toltalRate) {
        this.toltalRate = toltalRate;
    }

    public Set<Modify> getModifies() {
        return modifies;
    }

    public void setModifies(Set<Modify> modifies) {
        this.modifies = modifies;
    }

    public Set<Modify> getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Set<Modify> modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<Rate> getRateBy() {
        return rateBy;
    }

    public void setRateBy(Set<Rate> rateBy) {
        this.rateBy = rateBy;
    }

    public Set<Rate> getRated() {
        return rated;
    }

    public void setRated(Set<Rate> rated) {
        this.rated = rated;
    }

    public void removeAppointmentsJoin(Appointment appointment) {
        this.listAppointmentsJoin.remove(appointment);
        appointment.getParticipants().remove(this);
    }
}
