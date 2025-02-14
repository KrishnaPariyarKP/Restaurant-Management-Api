package com.example.demo.model.viewModel;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.model.OrderItem;
import com.example.demo.model.Reservation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllOrdersDTO {
	
	private Integer id;
	
	
    private List<OrderItemDTO> orderItems;

    private String reservation;
    
    private Integer createdBy;
    private Integer modifiedBy;
    private Double totalPrice; 
	
}
