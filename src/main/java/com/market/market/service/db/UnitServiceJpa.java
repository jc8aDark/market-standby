package com.market.market.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.market.model.Unit;
import com.market.market.repository.UnitRepository;
import com.market.market.service.IUnit;

@Service
public class UnitServiceJpa implements IUnit {
	
	@Autowired
	UnitRepository unitRepo;

	@Override
	public List<Unit> buscarTodas() {
		return unitRepo.findAll();
	}

}
