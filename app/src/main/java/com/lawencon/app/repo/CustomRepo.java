package com.lawencon.app.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class CustomRepo {

	@PersistenceContext
	protected EntityManager em;
}
