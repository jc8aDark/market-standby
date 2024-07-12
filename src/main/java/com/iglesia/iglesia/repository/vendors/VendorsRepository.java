package com.iglesia.iglesia.repository.vendors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iglesia.iglesia.model.vendors.Vendors;

public interface VendorsRepository extends JpaRepository<Vendors, Integer> {
	Vendors findByUserName(String userName);
}
