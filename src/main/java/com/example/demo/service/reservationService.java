package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Reservation;
import com.example.demo.model.UserInfo;
import com.example.demo.model.viewModel.ReservationDTO;
import com.example.demo.repository.ReservationRepo;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class reservationService {
	
	@Autowired
	ReservationRepo reservationRepo;
	
	@Autowired
	CommonService commonService;
	
	public List<ReservationDTO> getAllReservations() {
	    List<Reservation> reservations = reservationRepo.findAll();
	    return reservations.stream().map(reservation -> {
	        ReservationDTO dto = new ReservationDTO();
	        dto.setId(reservation.getId());
	        dto.setTableNumber(reservation.getTableNumber());
	        dto.setReservationTime(reservation.getReservationTime());
	        dto.setStatus(reservation.getStatus());
	        dto.setCreatedAt(reservation.getCreatedAt());
	        dto.setUserName(reservation.getUserInfo().getName());
	        return dto;
	    }).collect(Collectors.toList());
	}

	
	public String addReservation(Reservation mdl,HttpServletRequest request) {
		UserInfo userInfo = commonService.userdto(request).get();
		mdl.setUserInfo(userInfo);
		mdl.setReservationTime(LocalDateTime.now());
		mdl.setStatus("confirmed");
		mdl.setCreatedAt(LocalDateTime.now());
		reservationRepo.save(mdl);
		
		return "Reservation Confirmed";	
	}
	
	public String CancelReservation(Integer id) {
		Reservation  dt = reservationRepo.findById(id).get();
		dt.setStatus("cancelled");
		reservationRepo.save(dt);
		
		return "Reservation Cancelled";
	}
	
	public String modifyDt(Integer id,Reservation mdl) {
		Reservation  dt = reservationRepo.findById(id).get();
		dt.setTableNumber(mdl.getTableNumber());
		reservationRepo.save(dt);
		
		return "Reservation updated";
	}


	public String confimedReservation(Integer id) {
		Reservation  dt = reservationRepo.findById(id).get();
		dt.setStatus("confirmed");
		reservationRepo.save(dt);
		
		return "Reservation Confirmed";
	}

}
