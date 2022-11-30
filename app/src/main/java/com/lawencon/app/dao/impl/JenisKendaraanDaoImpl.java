package com.lawencon.app.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.dao.JenisKendaraanDao;
import com.lawencon.app.dao.repo.CustomRepo;
import com.lawencon.app.model.JenisKendaraan;

@Repository
public class JenisKendaraanDaoImpl extends CustomRepo implements JenisKendaraanDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<JenisKendaraan> findAll() throws Exception {
		Query q = em.createQuery("from JenisKendaraan");
		return q.getResultList();
	}

	@Override
	public void insert(JenisKendaraan jk) throws Exception {
		JenisKendaraan jns = new JenisKendaraan();
		jns.setJenis(jk.getJenis());
		em.persist(jns);
	}

	
}
