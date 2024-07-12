package com.market.market.repository.vendors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.market.model.vendors.Vendors;

public interface VendorsRepository extends JpaRepository<Vendors, Integer> {
	Vendors findByUserName(String userName);
}
