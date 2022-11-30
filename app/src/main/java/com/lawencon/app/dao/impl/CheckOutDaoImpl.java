package com.lawencon.app.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.app.dao.CheckOutDao;
import com.lawencon.app.dao.repo.CheckOutRepo;
import com.lawencon.app.dao.repo.CustomRepo;
import com.lawencon.app.model.CheckOut;

@Repository
public class CheckOutDaoImpl extends CustomRepo implements CheckOutDao {
	
	@Autowired
	private CheckOutRepo checkOutRepo;

	@Override
	public List<CheckOut> findAll() throws Exception {
		return checkOutRepo.findAll();
	}

	@Override
	public CheckOut findById(Integer id) {
		return checkOutRepo.findById(id).orElse(null);
	}

	@Override
	public Date findDateInById(Integer id) {
		Query q = em.createQuery("select timeIn from CheckIn where idIn = :idParam");
		q.setParameter("idParam", id);
		return (Date) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckOut> findByIdIn(Integer id) {
		Query q = em.createQuery("from CheckOut where checkIn.idIn = :idParam");
		q.setParameter("idParam", id);
		return q.getResultList();
	}

	@Override
	public void insert(CheckOut co) throws Exception {
		CheckOut newData = new CheckOut();
		newData.setCheckIn(co.getCheckIn());
		newData.setTimeOut(co.getTimeOut());
		em.persist(newData);
	}

	@Override
	public void update(CheckOut co) throws Exception {
		CheckOut cek = new CheckOut();
		cek = findById(co.getIdOut());
		cek.setCheckIn(co.getCheckIn());
		cek.setTimeOut(co.getTimeOut());
		em.merge(cek);
	}

	@Override
	public void delete(CheckOut co) throws Exception {
		CheckOut cek = new CheckOut();
		cek = findById(co.getIdOut());
		em.remove(cek);
	}

	
}
