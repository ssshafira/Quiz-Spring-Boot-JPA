package com.lawencon.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JenisKendaraan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idJenis;

	private String jenis;

	public Integer getIdJenis() {
		return idJenis;
	}

	public void setIdJenis(Integer idJenis) {
		this.idJenis = idJenis;
	}

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	
}
