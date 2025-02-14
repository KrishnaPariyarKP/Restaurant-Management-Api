package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Reservation;
import com.example.demo.model.viewModel.ReservationDTO;
import com.example.demo.service.reservationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reservation")
public class reservationController {
	
	@Autowired
	reservationService reservService;
	
	@GetMapping
	public List<ReservationDTO> getAllReservations(){
		return reservService.getAllReservations();
	}
	
	@PostMapping
	public String postReservation(@RequestBody Reservation mdl, HttpServletRequest request) {
		return reservService.addReservation(mdl, request);
	}
	
	@DeleteMapping("/{id}")
	public String cancelledReservation(@PathVariable Integer id) {
		return reservService.CancelReservation(id);
	}
	@PostMapping("/{id}")
	public String confimedReservation(@PathVariable Integer id) {
		return reservService.confimedReservation(id);
	}
	
	@PutMapping("/{id}")
	public String doUpdate(@PathVariable Integer id, @RequestBody Reservation mdl) {
		return reservService.modifyDt(id, mdl);
	}
	

}
