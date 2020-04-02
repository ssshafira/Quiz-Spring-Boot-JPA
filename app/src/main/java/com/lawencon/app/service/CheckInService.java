package com.lawencon.app.service;

import java.sql.Date;
import java.util.List;

import com.lawencon.app.model.CheckIn;


public interface CheckInService {

	abstract List<CheckIn> findAll() throws Exception;
	abstract List<CheckIn> findByPlatAndDate(String plat, Date time);
	
	abstract CheckIn findById(Integer id);
	
	abstract boolean insert(CheckIn ci) throws Exception;
	abstract void update(CheckIn ci) throws Exception;
	abstract void delete(CheckIn ci) throws Exception;
}
