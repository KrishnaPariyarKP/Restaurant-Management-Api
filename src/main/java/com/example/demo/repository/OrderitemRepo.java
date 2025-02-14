package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrderItem;

@Repository
public interface OrderitemRepo extends JpaRepository<OrderItem, Integer> {

    // Fetch OrderItems by their ID (existing method)
    @Query("select s from OrderItem s where s.id = ?1")
    List<OrderItem> getItemsById(Integer id);

   

    // Fetch OrderItems by User ID
    @Query("select s from OrderItem s where s.userInfo.id = ?1")
    List<OrderItem> findByUserId(Integer userId);

    // Fetch OrderItems by MenuItem ID
    @Query("select s from OrderItem s where s.menuItem.id = ?1")
    List<OrderItem> findByMenuItemId(Integer menuItemId);

    // Fetch all OrderItems for a specific reservation (optional if reservations are used)
    @Query("select s from OrderItem s where s.reservation.id = ?1")
    public List<OrderItem> findByReservationId(Integer reservationId);


    @Query("select s from OrderItem s where s.orders.id = ?1 and s.id = ?2")
    OrderItem getItemsByIidandOrdid(Integer orderId, Integer itemId);

}
