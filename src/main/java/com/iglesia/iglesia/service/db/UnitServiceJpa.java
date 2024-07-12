package com.iglesia.iglesia.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Unit;
import com.iglesia.iglesia.repository.UnitRepository;
import com.iglesia.iglesia.service.IUnit;

@Service
public class UnitServiceJpa implements IUnit {
	
	@Autowired
	UnitRepository unitRepo;

	@Override
	public List<Unit> buscarTodas() {
		return unitRepo.findAll();
	}

}
