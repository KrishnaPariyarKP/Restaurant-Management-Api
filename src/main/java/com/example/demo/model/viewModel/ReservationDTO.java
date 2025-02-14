package com.example.demo.model.viewModel;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Integer id;
    private Integer tableNumber;
    private LocalDateTime reservationTime;
    private String status;
    private LocalDateTime createdAt;
    private String userName; // Include only the fields you need
}
