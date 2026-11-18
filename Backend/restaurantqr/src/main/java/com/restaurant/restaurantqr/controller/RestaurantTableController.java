package com.restaurant.restaurantqr.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.restaurant.restaurantqr.entity.RestaurantTable;
import com.restaurant.restaurantqr.service.QRCodeService;
import com.restaurant.restaurantqr.service.RestaurantTableService;

@RestController
@RequestMapping("api/tables")
@CrossOrigin(origins={"http://localhost:5173", "http://10.88.19.221:5173"})
public class RestaurantTableController {

	private final RestaurantTableService tableService;
	public final QRCodeService qrCodeService;
	public RestaurantTableController(RestaurantTableService tableService,QRCodeService qrCodeService) {
		this.tableService=tableService;
		this.qrCodeService=qrCodeService;
	}
	@PostMapping
	public RestaurantTable addTable(@RequestBody RestaurantTable table) {
		return tableService.addTable(table);
	}

	@GetMapping
	public List<RestaurantTable> getAllTables(){
		return tableService.getAllTables();
	}

	@GetMapping("/{id}")
	public RestaurantTable getTableById(@PathVariable Long id) {
		return tableService.getTableById(id);
	}
	@PutMapping("/{id}")
	public RestaurantTable updateTable(@PathVariable Long id,@RequestBody RestaurantTable table) {
		return tableService.updateTable(id, table);
	}
	@DeleteMapping("/{id}")
	public String deleteTable(@PathVariable Long id) {
		tableService.deleteTable(id);
		return "restaurant table deleted Sucessfully";

	}
	@GetMapping("/{id}/qr")
	public String generateTableQRCode(@PathVariable Long id) throws WriterException,IOException{
		RestaurantTable table=tableService.getTableById(id);
		if(table==null) {
			return "Table Not Found";

		}
		String menuUrl="http://10.88.19.221.5173/menu/" + table.getQrToken();
		String qrCode=qrCodeService.genereteQrCode(menuUrl);
		return "data:image/png;base64,"+qrCode;
	}


}
