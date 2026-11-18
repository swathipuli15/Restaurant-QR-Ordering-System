package com.restaurant.restaurantqr.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurantqr.entity.TableSession;
import com.restaurant.restaurantqr.service.TableSessionService;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins={"http://localhost:5173","http://10.88.19.221:5173"})
public class TableSessionController {

	private final TableSessionService sessionService;
	public TableSessionController(TableSessionService sessionService) {
		this.sessionService=sessionService;
	}
	@PostMapping("/start/{qrToken}")
	public TableSession startSession(@PathVariable String qrToken) {
		return sessionService.startSession(qrToken);
	}
	@PutMapping("/close/{sessionId}")
	public TableSession closeSession(@PathVariable Long sessionId) {
		return sessionService.closeSession(sessionId);
	}

}
