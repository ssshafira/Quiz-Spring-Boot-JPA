package com.lawencon.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.app.model.CheckIn;
import com.lawencon.app.model.CheckOut;
import com.lawencon.app.model.JenisKendaraan;
import com.lawencon.app.service.CheckInService;
import com.lawencon.app.service.CheckOutService;
import com.lawencon.app.service.JenisKendaraanService;

@RestController
public class ParkiranController {
	
	/*
	 * Nama : Serenada Salma Shafira
	 */

	@Autowired
	private JenisKendaraanService jkService;

	@Autowired
	private CheckInService ciService;

	@Autowired
	private CheckOutService coService;

	@GetMapping("/jenis")
	public ResponseEntity<List<JenisKendaraan>> getAllJenis() {
		List<JenisKendaraan> listData = new ArrayList<JenisKendaraan>();
		try {
			listData = jkService.findAll();
		} catch (Exception e) {
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/jenis/persist")
	public ResponseEntity<List<JenisKendaraan>> persistJenis(@RequestBody JenisKendaraan jk) {
		try {
			jkService.insert(jk);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*** CRUD CHECK IN ***/

	@GetMapping("/checkin")
	public ResponseEntity<List<CheckIn>> getAllCheckIn() {
		List<CheckIn> listData = new ArrayList<CheckIn>();
		try {
			listData = ciService.findAll();
		} catch (Exception e) {
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkin/persist")
	public ResponseEntity<?> persistCheckIn(@RequestBody CheckIn ci) {
		try {
			return new ResponseEntity<>(ciService.insert(ci), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/checkin/merge")
	public ResponseEntity<?> mergeCheckIn(@RequestBody CheckIn ci) {
		try {
			return new ResponseEntity<>(ciService.update(ci), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/checkin/delete")
	public ResponseEntity<?> deleteCheckIn(@RequestBody CheckIn ci) {
		try {
			return new ResponseEntity<>(ciService.delete(ci), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/*** CRUD CHECK OUT ***/

	@GetMapping("/checkout")
	public ResponseEntity<List<CheckOut>> getAllCheckOut() {
		List<CheckOut> listData = new ArrayList<CheckOut>();
		try {
			listData = coService.findAll();
		} catch (Exception e) {
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkout/persist")
	public ResponseEntity<?> persistCheckOut(@RequestBody CheckOut co) {
		try {
			return new ResponseEntity<>(coService.insert(co), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/checkout/merge")
	public ResponseEntity<?> mergeCheckOut(@RequestBody CheckOut co) {
		try {
			return new ResponseEntity<>(coService.update(co), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/checkout/delete")
	public ResponseEntity<?> deleteCheckOut(@RequestBody CheckOut co) {
		try {
			return new ResponseEntity<>(coService.delete(co), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
