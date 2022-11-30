package com.lawencon.app.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.app.dao.CheckInDao;
import com.lawencon.app.dao.repo.CheckInRepo;
import com.lawencon.app.dao.repo.CustomRepo;
import com.lawencon.app.model.CheckIn;

@Repository
public class CheckInDaoImpl extends CustomRepo implements CheckInDao {
	
	@Autowired
	private CheckInRepo checkInRepo;

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckIn> findAll() throws Exception {
		Query q = em.createQuery("from CheckIn");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckIn> findByPlatAndDate(String plat, Date time) {
		Query q = em.createQuery("from CheckIn where plat = :platParam and timeIn = :timeParam");
		q.setParameter("platParam", plat);
		q.setParameter("timeParam", time);
		return q.getResultList();
	}

	@Override
	public CheckIn findById(Integer id) {
		return checkInRepo.findById(id).orElse(null);
	}

	@Override
	public void insert(CheckIn ci) throws Exception {
		CheckIn newData = new CheckIn();
		newData.setJenis(ci.getJenis());
		newData.setPlat(ci.getPlat());
		newData.setTimeIn(ci.getTimeIn());
		em.persist(newData);
	}

	@Override
	public void update(CheckIn ci) throws Exception {
		CheckIn newData = new CheckIn();
		newData = findById(ci.getIdIn());
		newData.setJenis(ci.getJenis());
		newData.setPlat(ci.getPlat());
		newData.setTimeIn(ci.getTimeIn());
		em.merge(newData);
	}

	@Override
	public void delete(CheckIn ci) throws Exception {
		CheckIn cek = new CheckIn();
		cek = findById(ci.getIdIn());
		em.remove(cek);
	}

}
