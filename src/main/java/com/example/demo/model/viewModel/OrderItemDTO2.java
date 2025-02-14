package com.example.demo.model.viewModel;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO2 {
    private Integer id;
    private String menuItemName;     // From MenuItem
    private Integer quantity;        // Order quantity
    private Double price;            // Price of the item

}
