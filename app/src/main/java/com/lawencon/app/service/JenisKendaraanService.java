package com.lawencon.app.service;

import java.util.List;

import com.lawencon.app.model.JenisKendaraan;

public interface JenisKendaraanService {

	abstract List<JenisKendaraan> findAll() throws Exception;
	abstract void insert(JenisKendaraan jk) throws Exception;
}
