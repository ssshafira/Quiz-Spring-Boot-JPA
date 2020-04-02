package com.lawencon.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.app.model.CheckIn;

@Repository
public interface CheckInRepo extends JpaRepository<CheckIn, Integer> {

}
