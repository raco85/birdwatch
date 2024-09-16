package com.radomir.drazic.birdwatchingapp.repository;

import com.radomir.drazic.birdwatchingapp.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("SELECT o FROM orders o WHERE LOWER(o.name) LIKE LOWER(CONCAT('%', :searchName, '%'))")
  List<Order> findOrdersByName(String searchName);

  @Query("SELECT o FROM orders o WHERE LOWER(o.latinName) LIKE LOWER(CONCAT('%', :latinName, '%'))")
  List<Order> findOrdersByLatinName(String latinName);
}