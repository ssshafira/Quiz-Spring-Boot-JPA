package com.lawencon.app.service;

import java.sql.Date;
import java.util.List;

import com.lawencon.app.model.CheckOut;

public interface CheckOutService {

	abstract List<CheckOut> findAll() throws Exception;
	
	abstract CheckOut findById(Integer id);
	abstract Date findDateInById(Integer id);
	abstract List<CheckOut> findByIdIn(Integer id);
	
	abstract String insert(CheckOut co) throws Exception;
	abstract String update(CheckOut co) throws Exception;
	abstract String delete(CheckOut co) throws Exception;
}
