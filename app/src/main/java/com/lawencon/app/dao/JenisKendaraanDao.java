package com.lawencon.app.dao;

import java.util.List;

import com.lawencon.app.model.JenisKendaraan;

public interface JenisKendaraanDao {

	abstract List<JenisKendaraan> findAll() throws Exception;
	abstract void insert(JenisKendaraan jk) throws Exception;
}
