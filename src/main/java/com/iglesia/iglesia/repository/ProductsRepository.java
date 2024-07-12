package com.iglesia.iglesia.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.iglesia.iglesia.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
	
}
