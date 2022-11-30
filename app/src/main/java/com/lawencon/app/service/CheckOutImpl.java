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
@Transactional
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CheckOut> findByIdIn(Integer id) {
		Query q = em.createQuery("from CheckOut where checkIn.idIn = :idParam");
		q.setParameter("idParam", id);
		return q.getResultList();
	}

	@Override
	public String insert(CheckOut co) throws Exception {
		Date dateIn = coService.findDateInById(co.getCheckIn().getIdIn());
		List<CheckOut> cek = coService.findByIdIn(co.getCheckIn().getIdIn());
		if (cek.isEmpty()) {
			if (dateIn.before(co.getTimeOut()) || dateIn.equals(co.getTimeOut())) {
				CheckOut newData = new CheckOut();
				newData.setCheckIn(co.getCheckIn());
				newData.setTimeOut(co.getTimeOut());
				em.persist(newData);
			} else {
				throw new Exception ("Tanggal Check Out harus >= tanggal Check In!");
			}
		} else {
			throw new Exception ("Plat ini sudah melakukan Check Out pada " + cek.get(0).getTimeOut());
		}
		return "Berhasil Check Out!";
	}

	@Override
	public String update(CheckOut co) throws Exception {
		Date dateIn = coService.findDateInById(co.getCheckIn().getIdIn());
		if (dateIn.before(co.getTimeOut()) || dateIn.equals(co.getTimeOut())) {
			CheckOut cek = new CheckOut();
			cek = findById(co.getIdOut());
			cek.setCheckIn(co.getCheckIn());
			cek.setTimeOut(co.getTimeOut());
			em.merge(cek);
		} else {
			throw new Exception ("Tanggal Check Out harus >= tanggal Check In!");
		}
		return "Berhasil edit Check Out!";
	}

	@Override
	public String delete(CheckOut co) throws Exception {
		CheckOut cek = new CheckOut();
		cek = findById(co.getIdOut());
		em.remove(cek);
		return "Berhasil hapus Check Out!";
	}

}
