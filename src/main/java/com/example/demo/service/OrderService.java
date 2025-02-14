package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MenuItem;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Orders;
import com.example.demo.model.UserInfo;
import com.example.demo.model.viewModel.AllOrdersDTO;
import com.example.demo.model.viewModel.OrderItemDTO;
import com.example.demo.model.viewModel.OrderItemDTO2;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.OrderitemRepo;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class OrderService {

 	@Autowired
 	OrderitemRepo orderitemRepo;
 	@Autowired
 	OrderRepo orderRepo;
 	
 	@Autowired
 	CommonService commonService;
 	
 	@Autowired
 	menuService mService;
 	
 	public List<OrderItemDTO> getOrderItemsByOrderId(Integer id) {
        List<OrderItem> orderItems = orderitemRepo.getItemsById(id);
      
        return orderItems.stream().map(orderItem -> {
            OrderItemDTO dto = new OrderItemDTO();
            dto.setId(orderItem.getId());
            dto.setUserName(orderItem.getUserInfo().getName());
            dto.setMenuItemName(orderItem.getMenuItem().getName());
            dto.setQuantity(orderItem.getQuantity());
            dto.setPrice(orderItem.getPrice());
            dto.setReservationInfo(orderItem.getReservation() != null 
                ? orderItem.getReservation().getTableNumber().toString()
                : "No Reservation");
            dto.setCreatedAt(orderItem.getCreatedAt());
            return dto;
        }).collect(Collectors.toList());
        
    }

 	public String addOrder(OrderItem mdl, HttpServletRequest request) {
 	    UserInfo userInfo = commonService.userdto(request).get();
 	    MenuItem menuItem = mService.getItem(mdl.getMenuItem().getId());
 	    Orders orders = new Orders();
 	    
 	    mdl.setCreatedAt(LocalDateTime.now());  // Make sure this line is present before saving
 	    mdl.setUserInfo(userInfo);
 	    mdl.setPrice(mdl.getQuantity() * menuItem.getPrice());

 	    // For new orders
 	    if (mdl.getOrders() == null || mdl.getOrders().getId() == null) {
 	        List<OrderItem> items = new ArrayList<>();
 	        items.add(mdl);
 	        orders.setOrderItems(items);
 	        orders.setReservation(mdl.getReservation());
 	        orders.setCreatedBy(userInfo.getId());
 	        orders.setModifiedBy(null);
 	        orders.setTotalPrice(mdl.getQuantity() * menuItem.getPrice());
 	       
 	        orderRepo.save(orders);
 	    } else {
 	        // For existing orders
 	        orders = orderRepo.findById(mdl.getOrders().getId()).get();
 	        if (orders != null) {
 	            List<OrderItem> orderAddItems = orders.getOrderItems();
 	            orderAddItems.add(mdl);
 	            orders.setOrderItems(orderAddItems);
 	            orders.setReservation(mdl.getReservation());
 	            orders.setModifiedBy(userInfo.getId());
 	            orders.setTotalPrice(orders.getTotalPrice() + (mdl.getQuantity() * menuItem.getPrice()));
 	           // orderRepo.save(orders);
 	        }
 	    }
 	    // Set the required 'createdAt' field before saving the OrderItem
 	    mdl.setOrders(orders);
 	    orderitemRepo.save(mdl);  // Save the order item with 'createdAt' set

 	    return "Successfully Ordered";
 	}

 	public List<AllOrdersDTO> getAllOrder() {
 	    List<Orders> ordersList = orderRepo.findAll();

 	    return ordersList.stream().map(order -> {
 	        AllOrdersDTO dto = new AllOrdersDTO();
 	        dto.setId(order.getId());
 	        dto.setReservation(order.getReservation() != null ? order.getReservation().getTableNumber().toString() : null);
 	        dto.setCreatedBy(order.getCreatedBy());
 	        dto.setModifiedBy(order.getModifiedBy());
 	        dto.setTotalPrice(order.getTotalPrice());

 	        List<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream().map(item -> {
 	            OrderItemDTO itemDTO = new OrderItemDTO();
 	            itemDTO.setId(item.getId());
 	            itemDTO.setUserName(item.getUserInfo().getName());
 	            itemDTO.setMenuItemName(item.getMenuItem().getName());
 	            itemDTO.setQuantity(item.getQuantity());
 	            itemDTO.setPrice(item.getPrice());
 	            itemDTO.setReservationInfo(item.getReservation() != null ? item.getReservation().getTableNumber().toString() : null);
 	            itemDTO.setCreatedAt(item.getCreatedAt());
 	            return itemDTO;
 	        }).collect(Collectors.toList());

 	        dto.setOrderItems(orderItemDTOs);
 	        return dto;
 	    }).collect(Collectors.toList());
 	}

	public String deleteOrderItem(Integer order_id,Integer item_id) {
		 OrderItem orderItems = orderitemRepo.getItemsByIidandOrdid(order_id,item_id);
		 if(orderItems != null) {
			 orderitemRepo.delete(orderItems);
			 return "Item Deleted";
		 }
		 
		return "There is no items ordered";
	}


}
