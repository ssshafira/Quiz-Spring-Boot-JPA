package com.lawencon.app.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.model.JenisKendaraan;
import com.lawencon.app.repo.CustomRepo;

@Service
public class JenisKendaraanImpl extends CustomRepo implements JenisKendaraanService {

	@Override
	@Transactional
	public void insert(JenisKendaraan jk) {
		JenisKendaraan jns = new JenisKendaraan();
		jns.setJenis(jk.getJenis());
		em.persist(jns);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JenisKendaraan> findAll() throws Exception {
		Query q = em.createQuery("from JenisKendaraan");
		return q.getResultList();
	}
	
}
