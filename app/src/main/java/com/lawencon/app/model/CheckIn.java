package com.lawencon.app.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CheckIn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idIn;
	
	@OneToOne
	@JoinColumn(name = "idJenis", nullable = false)
	private JenisKendaraan jenis;
	
	private String plat;
	private Date timeIn;
	public Integer getIdIn() {
		return idIn;
	}
	public void setIdIn(Integer idIn) {
		this.idIn = idIn;
	}
	public JenisKendaraan getJenis() {
		return jenis;
	}
	public void setJenis(JenisKendaraan jenis) {
		this.jenis = jenis;
	}
	public String getPlat() {
		return plat;
	}
	public void setPlat(String plat) {
		this.plat = plat;
	}
	public Date getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}
	
	
	
	
}
