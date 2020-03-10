package com.coxauto.storemanagementsystem.repository;

import java.util.List;

import com.coxauto.storemanagementsystem.model.Store;

public interface StoreRepository {

	public List<Store> findAll();
	public Store findOne(Long storeId);
	public Store save(Store store);
	public Store delete(Long storeId);
	
}
