package com.studywithme.model;

import jakarta.persistence.*;

@Entity
public class Rate extends AbstractModel{
	private Integer point;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "rate_by")
	private User rateBy;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "reted")
	private User rated;
	public Rate() {
	}

	public User getRateBy() {
		return rateBy;
	}

	public void setRateBy(User rateBy) {
		this.rateBy = rateBy;
	}

	public Rate(Integer id, Integer point) {
		this.id = id;
		this.point = point;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public User getRated() {
		return rated;
	}

	public void setRated(User rated) {
		this.rated = rated;
	}
}
