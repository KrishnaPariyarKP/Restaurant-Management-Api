package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MenuItem;
import com.example.demo.service.menuService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/menu")
public class menuController {

	@Autowired
	menuService mService;
	
	@GetMapping()
	public List<MenuItem> getAllTems() {
		return mService.getAllMenuItems();
	}
	
	@GetMapping("/{name}")
	public List<MenuItem> getItemByname(@PathVariable String name) {
		return mService.getItemByName(name);
	}
	
	@PostMapping("/add")
	public String PostMenuItem(@RequestBody MenuItem mdl) {
		return mService.addMenu(mdl);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteItem(@PathVariable Integer id) {
		return mService.removeMenuItem(id);
	}
	
	@PutMapping("/update/{id}")
	public String postUpdate(@PathVariable Integer id, @RequestBody MenuItem mdl) {
		return mService.modifyItem(id, mdl);
	}
	
	
	
}
