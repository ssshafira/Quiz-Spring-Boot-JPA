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
			if (ciService.insert(ci)) {
				ciService.insert(ci);
				return new ResponseEntity<>("berhasil cek in", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						"plat salah atau sudah cek in di hari yang sama\npisahkan plat dgn spasi (contoh: B 123 ER)",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/checkin/merge")
	public ResponseEntity<List<CheckIn>> mergeCheckIn(@RequestBody CheckIn ci) {
		try {
			ciService.update(ci);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/checkin/delete")
	public ResponseEntity<List<CheckIn>> deleteCheckIn(@RequestBody CheckIn ci) {
		try {
			ciService.delete(ci);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
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
			if (coService.insert(co)) {
				coService.insert(co);
				return new ResponseEntity<>("berhasil cek out", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("tanggal cek out harus setelah cek in", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/checkout/merge")
	public ResponseEntity<List<CheckIn>> mergeCheckOut(@RequestBody CheckOut co) {
		try {
			coService.update(co);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/checkout/delete")
	public ResponseEntity<List<CheckOut>> deleteCheckOut(@RequestBody CheckOut co) {
		try {
			coService.delete(co);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public static boolean isParsable(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
