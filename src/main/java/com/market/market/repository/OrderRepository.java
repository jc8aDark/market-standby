package com.market.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.market.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
