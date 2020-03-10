package com.coxauto.storemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coxauto.storemanagementsystem.model.Store;
import com.coxauto.storemanagementsystem.service.StoreManagementServiceImpl;

@Controller
public class StoreManagementController {

	@Autowired
	StoreManagementServiceImpl storeMngService;
	
	// Gets all stores in DB
	@GetMapping("/store")
	@ResponseBody
	public ResponseEntity<List<Store>> getAllStores() {
		System.out.println("hit");
		return ResponseEntity.ok().header("Access-Control-Allow-Origin", "localhost:4200").body(storeMngService.getAll());
	}
	
	@GetMapping("/store/{storeId}")
	@ResponseBody
	public Store getStore(@PathVariable("storeId") Long storeId) {
		return storeMngService.getOneById(storeId);
	}
	
	// Insert new Store into DB
	@PostMapping("/store")
	@ResponseBody
	public Store addStore(@RequestBody Store store) {
		return storeMngService.upsert(store);
	}
	
	// Update existing Store in DB
	@PutMapping("/store")
	@ResponseBody
	public Store updateStore(@RequestBody Store store) {
		return storeMngService.upsert(store);
	}
	
	// Delete specified store in DB
	@DeleteMapping("/store/{storeId}")
	@ResponseBody
	public Store addStore(@PathVariable("storeId") Long storeId) {
		return storeMngService.remove(storeId);
	}
}