package com.market.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.market.model.Countries;

public interface CountriesRepository extends JpaRepository<Countries, Integer> {

}
