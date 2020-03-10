package com.coxauto.storemanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxauto.storemanagementsystem.model.Store;
import com.coxauto.storemanagementsystem.repository.StoreRepositoryImpl;

// This layer is not necessary in this simplified application 
// It is included to denote separation of API and service logic
@Component
public class StoreManagementServiceImpl implements StoreManagementService {

	@Autowired
	StoreRepositoryImpl storeRepo;
	
	@Override
	public List<Store> getAll() {
		return storeRepo.findAll();
	}

	@Override
	public Store getOneById(Long storeId) {
		return storeRepo.findOne(storeId);
	}

	@Override
	public Store upsert(Store store) {
		return storeRepo.save(store);
	}

	@Override
	public Store remove(Long storeId) {
		return storeRepo.delete(storeId);
	}

}
