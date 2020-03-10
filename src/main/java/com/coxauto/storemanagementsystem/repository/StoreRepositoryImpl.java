package com.coxauto.storemanagementsystem.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.coxauto.storemanagementsystem.model.Store;

// Mocks the interaction with a relational database
@Component
public class StoreRepositoryImpl implements StoreRepository {

	static Map<Long, Store> stores;
	static Long nextKey = 1L;

	public StoreRepositoryImpl() {
		stores = initializeStores();
	}

	@Override
	public List<Store> findAll() {
		return stores.values().stream().collect(Collectors.toList());
	}

	@Override
	public Store findOne(Long storeId) {
		return stores.get(storeId);
	}

	@Override
	public Store save(Store store) {
		if (null == store.getId()) {
			store.setId(nextKey);
			nextKey++;
		}
		stores.put(store.getId(), store);
		return store;
	}

	@Override
	public Store delete(Long storeId) {
		return stores.remove(storeId);
	}

	// Runs once to set up the Mock DB
	private Map<Long, Store> initializeStores() {
		ObjectMapper om = new ObjectMapper();

		try {
			Path p = Paths.get("src/main/resources/static/stores.json");
			System.out.println(p.toString());
			return om.readValue(new String(Files.readAllBytes(p)), new TypeReference<List<Store>>() {}).stream()
					.map(store -> updateKey(store))
					.collect(Collectors.toMap(Store::getId, Function.identity()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new HashMap<>();
	}
	
	private Store updateKey(Store store) {
		if (store.getId() > nextKey) {
			nextKey = store.getId() + 1L;
		}
		return store;
	}

}
