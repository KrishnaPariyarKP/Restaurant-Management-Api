package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

}
