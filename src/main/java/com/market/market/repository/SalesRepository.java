package com.market.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.market.model.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer> {

}
