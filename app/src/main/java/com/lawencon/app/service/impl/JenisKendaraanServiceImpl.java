package com.lawencon.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.dao.JenisKendaraanDao;
import com.lawencon.app.model.JenisKendaraan;
import com.lawencon.app.service.JenisKendaraanService;

@Service
public class JenisKendaraanServiceImpl implements JenisKendaraanService {
	
	@Autowired
	private JenisKendaraanDao jenisKendaraanDao;
	
	@Override
	public List<JenisKendaraan> findAll() throws Exception {
		return jenisKendaraanDao.findAll();
	}

	@Override
	@Transactional
	public void insert(JenisKendaraan jk) throws Exception {
		jenisKendaraanDao.insert(jk);
	}
	
}
