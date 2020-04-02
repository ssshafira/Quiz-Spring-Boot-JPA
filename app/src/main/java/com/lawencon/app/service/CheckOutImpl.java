package com.lawencon.app.service;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.model.CheckOut;
import com.lawencon.app.repo.CheckOutRepo;
import com.lawencon.app.repo.CustomRepo;

@Service
public class CheckOutImpl extends CustomRepo implements CheckOutService {

	@Autowired
	private CheckOutRepo coRepo;

	@Autowired
	private CheckOutService coService;

	@Override
	public List<CheckOut> findAll() throws Exception {
		return coRepo.findAll();
	}

	@Override
	public CheckOut findById(Integer id) {
		return coRepo.findById(id).orElse(null);
	}

	@Override
	public Date findDateInById(Integer id) {
		Query q = em.createQuery("select timeIn from CheckIn where idIn = :idParam");
		q.setParameter("idParam", id);
		return (Date) q.getSingleResult();
	}

	@Override
	@Transactional
	public boolean insert(CheckOut co) throws Exception {
		boolean stat = false;
		Date dt = coService.findDateInById(co.getCheckIn().getIdIn());
		if (dt.before(co.getTimeOut())) {
			CheckOut cek = new CheckOut();
			cek.setCheckIn(co.getCheckIn());
			cek.setTimeOut(co.getTimeOut());
			em.persist(cek);
			stat = true;
		} else stat = false;
		return stat;
	}

	@Override
	@Transactional
	public void update(CheckOut co) throws Exception {
		CheckOut cek = new CheckOut();
		cek = findById(co.getIdOut());
		cek.setCheckIn(co.getCheckIn());
		cek.setTimeOut(co.getTimeOut());
		em.merge(cek);
	}

	@Override
	@Transactional
	public void delete(CheckOut co) throws Exception {
		CheckOut cek = new CheckOut();
		cek = findById(co.getIdOut());
		em.remove(cek);
	}

}
