package com.vcl.dao;

import java.util.List;

public interface Dao<ENTITY, KEY> {

	ENTITY persist(ENTITY entity);
	ENTITY merge(ENTITY entity);
	void remove(ENTITY entity);
	void removeById(KEY key);
	ENTITY refresh(ENTITY entity);
	ENTITY findById(KEY key);
	List<ENTITY> findAll();
	List<ENTITY> findAllPaged(int firstResult, int maxResult);

}
