package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MenuItem;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, Integer>{

	@Query("select s from MenuItem s where s.name = ?1 ")
	public Optional<List<MenuItem>> findByName(String name);

}
