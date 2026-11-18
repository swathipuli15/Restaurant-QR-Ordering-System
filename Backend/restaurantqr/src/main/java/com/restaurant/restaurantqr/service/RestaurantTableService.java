package com.restaurant.restaurantqr.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.restaurant.restaurantqr.entity.RestaurantTable;
import com.restaurant.restaurantqr.repository.RestaurantTableRepository;

@Service
public class RestaurantTableService {
	private final RestaurantTableRepository tableRepository;
//	public RestaurantTableService() {
//		this.tableRepository = null;
//
//	}
	public RestaurantTableService(RestaurantTableRepository tableRepository) {
		this.tableRepository=tableRepository;
	}

	//add Table
	public RestaurantTable addTable(RestaurantTable table) {
		if(tableRepository.existsByTableNumber(table.getTableNumber())) {
			throw new RuntimeException("Table Number Already Exits");
		}
		String token=UUID.randomUUID().toString();
		table.setQrToken(token);
		table.setStatus("Available");
		return tableRepository.save(table);

	}

	//Get All Tables
	public List<RestaurantTable> getAllTables(){
		return tableRepository.findAll();

	}

	public RestaurantTable getTableById(Long id) {
		return tableRepository.findById(id).orElse(null);
	}
	public RestaurantTable updateTable(Long id,RestaurantTable table) {
		RestaurantTable existingTable=tableRepository.findById(id).orElse(null);
		if(existingTable!=null) {
			existingTable.setTableNumber(table.getTableNumber());
			existingTable.setStatus(table.getStatus());
			return tableRepository.save(existingTable);
		}
		return null;
	}
	public void deleteTable(Long id) {
		tableRepository.deleteById(id);


	}


}
