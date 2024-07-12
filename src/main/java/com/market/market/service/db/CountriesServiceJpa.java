package com.market.market.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.market.model.Countries;
import com.market.market.repository.CountriesRepository;
import com.market.market.service.ICountries;

@Service
public class CountriesServiceJpa implements ICountries {
	
	@Autowired
	private CountriesRepository countriesRepo;

	@Override
	public List<Countries> buscarTodas() {
		return countriesRepo.findAll();
	}

}
