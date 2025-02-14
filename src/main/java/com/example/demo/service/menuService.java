package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MenuItem;
import com.example.demo.repository.MenuItemRepo;
import com.example.demo.repository.ReservationRepo;

@Service
public class menuService {

	@Autowired
	MenuItemRepo menuItemRepo;
	
	public List<MenuItem> getAllMenuItems(){
		return menuItemRepo.findAll();
	}
	
	public MenuItem getItem(Integer id) {
		Optional<MenuItem>  item = menuItemRepo.findById(id);
		return item.get();
	}
	
	public List<MenuItem> getItemByName(String name){
		return menuItemRepo.findByName(name).get();
	}
	
	public String addMenu(MenuItem mdl) {
		mdl.setCreatedAt(LocalDateTime.now());
		menuItemRepo.save(mdl);
		return "Item successfully added";
	}
	
	public String removeMenuItem(Integer id) {
		menuItemRepo.deleteById(id);
		return "Item Sucessfully Deleted";
	}
	
	public String modifyItem(Integer id, MenuItem mdl) {
		MenuItem item = getItem(id);
		if (item != null) {
			item.setName(mdl.getName());
			item.setDescription(mdl.getDescription());
			item.setPrice(mdl.getPrice());
			item.setCategory(mdl.getCategory());
			menuItemRepo.save(item);
			return "Item Successfully Updated";
		}
		return "Unable to Update";

	}
}
