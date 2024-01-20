package com.studywithme.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Modify {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id_modify")
	private User modifyBy;
	private Date modifyDate;

	private String detail;

	// Modified
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_modified")
	private User userModified;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_modified")
	private Appointment appointmentModified;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "school_modified")
	private School schoolModified;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name = "address_modified")
	private Address addressModified;


	public Modify() {
	}

	public Modify(Integer id, User modifyBy, Date modifyDate) {
		this.id = id;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(User modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public User getUserModified() {
		return userModified;
	}

	public void setUserModified(User userModified) {
		this.userModified = userModified;
	}

	public Appointment getAppointmentModified() {
		return appointmentModified;
	}

	public void setAppointmentModified(Appointment appointmentModified) {
		this.appointmentModified = appointmentModified;
	}

	public School getSchoolModified() {
		return schoolModified;
	}

	public void setSchoolModified(School schoolModified) {
		this.schoolModified = schoolModified;
	}

	public Address getAddressModified() {
		return addressModified;
	}

	public void setAddressModified(Address addressModified) {
		this.addressModified = addressModified;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
