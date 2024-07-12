package com.market.market.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.market.market.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
	
}
