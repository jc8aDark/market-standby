package com.iglesia.iglesia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iglesia.iglesia.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
