package com.lawencon.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.app.model.CheckOut;

@Repository
public interface CheckOutRepo extends JpaRepository<CheckOut, Integer> {

}
