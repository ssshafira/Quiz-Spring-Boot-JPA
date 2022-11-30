package com.lawencon.app.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.dao.CheckOutDao;
import com.lawencon.app.model.CheckOut;
import com.lawencon.app.service.CheckOutService;

@Service
@Transactional
public class CheckOutServiceImpl implements CheckOutService {

	@Autowired
	private CheckOutDao checkOutDao;

	@Override
	public List<CheckOut> findAll() throws Exception {
		return checkOutDao.findAll();
	}

	@Override
	public CheckOut findById(Integer id) {
		return checkOutDao.findById(id);
	}

	@Override
	public Date findDateInById(Integer id) {
		return checkOutDao.findDateInById(id);
	}
	
	@Override
	public List<CheckOut> findByIdIn(Integer id) {
		return checkOutDao.findByIdIn(id);
	}

	@Override
	public String insert(CheckOut co) throws Exception {
		Date dateIn = findDateInById(co.getCheckIn().getIdIn());
		List<CheckOut> cek = findByIdIn(co.getCheckIn().getIdIn());
		if (cek.isEmpty()) {
			if (dateIn.before(co.getTimeOut()) || dateIn.equals(co.getTimeOut())) {
				checkOutDao.insert(co);
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
		Date dateIn = findDateInById(co.getCheckIn().getIdIn());
		if (dateIn.before(co.getTimeOut()) || dateIn.equals(co.getTimeOut())) {
			checkOutDao.update(co);
		} else {
			throw new Exception ("Tanggal Check Out harus >= tanggal Check In!");
		}
		return "Berhasil edit Check Out!";
	}

	@Override
	public String delete(CheckOut co) throws Exception {
		checkOutDao.delete(co);
		return "Berhasil hapus Check Out!";
	}

}
