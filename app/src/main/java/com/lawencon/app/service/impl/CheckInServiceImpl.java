package com.lawencon.app.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.model.CheckIn;
import com.lawencon.app.dao.CheckInDao;
import com.lawencon.app.service.CheckInService;

@Service
@Transactional
public class CheckInServiceImpl implements CheckInService {

	@Autowired
	private CheckInDao checkInDao;

	@Override
	public List<CheckIn> findAll() throws Exception {
		return checkInDao.findAll();
	}

	@Override
	public List<CheckIn> findByPlatAndDate(String plat, Date time) {
		return checkInDao.findByPlatAndDate(plat, time);
	}
	
	@Override
	public CheckIn findById(Integer id) {
		return checkInDao.findById(id);
	}

	@Override
	public String insert(CheckIn ci) throws Exception {
		List<CheckIn> cek = findByPlatAndDate(ci.getPlat(), ci.getTimeIn());
		if (cek.isEmpty()) {
			if (cekPlat(ci.getPlat())) {
				checkInDao.insert(ci);
			} else {
				throw new Exception("Plat tidak sesuai");
			}
		} else throw new Exception("Plat ini sudah melakukan Check In pada " + cek.get(0).getTimeIn());
		return "Berhasil Check In!";
	}

	@Override
	public String update(CheckIn ci) throws Exception {	
		List<CheckIn> cek = findByPlatAndDate(ci.getPlat(), ci.getTimeIn());
		if (cek.isEmpty()) {
			if (cekPlat(ci.getPlat())) {
				checkInDao.update(ci);
			} else {
				throw new Exception("Plat tidak sesuai");
			}
		} else throw new Exception("Plat ini sudah melakukan Check In pada " + cek.get(0).getTimeIn());
		return "Berhasil edit Check In!";
	}

	@Override
	public String delete(CheckIn ci) throws Exception {
		checkInDao.delete(ci);
		return "Berhasil hapus Check In!";
	}

	public static boolean isParsable(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean cekPlat(String plat) throws Exception {
		boolean stat = false;
		String[] tempPlat;
		tempPlat = plat.split(" ");
		if (tempPlat[0].matches("^[a-zA-Z]*$") && (tempPlat.length == 3)) {
			if (tempPlat[1].length() > 0 && tempPlat[1].length() <= 4 && isParsable(tempPlat[1])) {
				char[] chars = tempPlat[2].toCharArray();
				for (char c : chars) {
					if (Character.isDigit(c)) {
						stat = false;
						break;
					} else
						stat = true;
				}
				if (tempPlat[2].length() > 0 && tempPlat[2].length() <= 3) {
					stat = true;
				} else {
					stat = false;
				}
			}
		}
		return stat;
	}
}
