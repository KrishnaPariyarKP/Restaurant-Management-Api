package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OrderItem;
import com.example.demo.model.Orders;
import com.example.demo.model.UserInfo;
import com.example.demo.model.viewModel.AllOrdersDTO;
import com.example.demo.model.viewModel.OrderItemDTO;
import com.example.demo.security.JwtService;
import com.example.demo.security.UserInfoService;
import com.example.demo.service.CommonService;
import com.example.demo.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/order")
public class orderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	CommonService commonService;
	
	@GetMapping
	public List<AllOrdersDTO> getAllOrder(){
		return orderService.getAllOrder();
	}
	@GetMapping("/{id}")
	public List<OrderItemDTO> getOrders(@PathVariable Integer id){
	
	    return orderService.getOrderItemsByOrderId(id);
	}
	
	@PostMapping
	public String postOrder(@RequestBody OrderItem mdl, HttpServletRequest request) {
		return orderService.addOrder(mdl, request);
	}
	
	@DeleteMapping("/{order_id}/{item_id}")
	public String deleteOrderItem(@PathVariable Integer order_id,@PathVariable Integer item_id) {
		return orderService.deleteOrderItem(order_id,item_id);
	}
	
}
