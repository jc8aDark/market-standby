package com.iglesia.iglesia.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Countries;
import com.iglesia.iglesia.repository.CountriesRepository;
import com.iglesia.iglesia.service.ICountries;

@Service
public class CountriesServiceJpa implements ICountries {
	
	@Autowired
	private CountriesRepository countriesRepo;

	@Override
	public List<Countries> buscarTodas() {
		return countriesRepo.findAll();
	}

}
