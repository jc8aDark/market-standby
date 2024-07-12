package com.iglesia.iglesia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Unit;

@Service
public interface IUnit {
	
	List<Unit>buscarTodas();

}
