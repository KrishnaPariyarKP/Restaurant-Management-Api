package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo userInfo; // Field name must match `mappedBy` in UserInfo

    
	@Column(name = "table_number", nullable = false)
    private Integer tableNumber;

    @Column(name = "reservation_time", nullable = false)
    private LocalDateTime reservationTime;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<OrderItem> orderItem;
    
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Orders> orders;
   
}
