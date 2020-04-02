package com.lawencon.app.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CheckOut {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idOut;
	
	@OneToOne
	@JoinColumn(name = "idIn", nullable = false)
	private CheckIn checkIn;
	
	private Date timeOut;

	public Integer getIdOut() {
		return idOut;
	}

	public void setIdOut(Integer idOut) {
		this.idOut = idOut;
	}

	public CheckIn getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(CheckIn checkIn) {
		this.checkIn = checkIn;
	}

	public Date getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}

	
	
	
}
