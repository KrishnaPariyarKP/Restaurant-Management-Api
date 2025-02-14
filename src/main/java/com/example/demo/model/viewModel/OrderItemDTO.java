package com.example.demo.model.viewModel;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Integer id;
    private String userName;         // From UserInfo
    private String menuItemName;     // From MenuItem
    private Integer quantity;        // Order quantity
    private Double price;            // Price of the item
    private String reservationInfo;  // From Reservation (optional)
    private LocalDateTime createdAt; // Creation timestamp
}
