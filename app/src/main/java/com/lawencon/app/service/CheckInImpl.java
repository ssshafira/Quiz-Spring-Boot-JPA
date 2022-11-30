package com.lawencon.app.service;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.model.CheckIn;
import com.lawencon.app.repo.CheckInRepo;
import com.lawencon.app.repo.CustomRepo;

@Service
@Transactional
public class CheckInImpl extends CustomRepo implements CheckInService {

	@Autowired
	private CheckInRepo ciRepo;

	@Autowired
	private CheckInService ciService;

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
	public String insert(CheckIn ci) throws Exception {
		List<CheckIn> cek = ciService.findByPlatAndDate(ci.getPlat(), ci.getTimeIn());
		if (cek.isEmpty()) {
			if (cekPlat(ci.getPlat())) {
				CheckIn newData = new CheckIn();
				newData.setJenis(ci.getJenis());
				newData.setPlat(ci.getPlat());
				newData.setTimeIn(ci.getTimeIn());
				em.persist(newData);
			} else {
				throw new Exception("Plat tidak sesuai");
			}
		} else throw new Exception("Plat ini sudah melakukan Check In pada " + cek.get(0).getTimeIn());
		return "Berhasil Check In!";
	}

	@Override
	public String update(CheckIn ci) throws Exception {	
		List<CheckIn> cek = ciService.findByPlatAndDate(ci.getPlat(), ci.getTimeIn());
		if (cek.isEmpty()) {
			if (cekPlat(ci.getPlat())) {
				CheckIn newData = new CheckIn();
				newData = findById(ci.getIdIn());
				newData.setJenis(ci.getJenis());
				newData.setPlat(ci.getPlat());
				newData.setTimeIn(ci.getTimeIn());
				em.merge(newData);
			} else {
				throw new Exception("Plat tidak sesuai");
			}
		} else throw new Exception("Plat ini sudah melakukan Check In pada " + cek.get(0).getTimeIn());
		return "Berhasil edit Check In!";
	}

	@Override
	public String delete(CheckIn ci) throws Exception {
		CheckIn cek = new CheckIn();
		cek = findById(ci.getIdIn());
		em.remove(cek);
		return "Berhasil hapus Check In!";
	}

	@Override
	public CheckIn findById(Integer id) {
		return ciRepo.findById(id).orElse(null);
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
