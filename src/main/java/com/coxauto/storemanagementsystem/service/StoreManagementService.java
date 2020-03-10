package com.coxauto.storemanagementsystem.service;

import java.util.List;

import com.coxauto.storemanagementsystem.model.Store;

public interface StoreManagementService {
	public List<Store> getAll();
	public Store getOneById(Long storeId);
	public Store upsert(Store store);
	public Store remove(Long storeId);
}