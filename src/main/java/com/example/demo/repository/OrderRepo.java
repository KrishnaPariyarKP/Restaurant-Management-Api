package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Orders;



@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {
	
//	@Query("select s from orders as s where s.user_id = ?1 ")
//	public Optional<List<Orders>> getAllById(Integer user_id);

}
