package com.market.market.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.market.market.model.Unit;

@Service
public interface IUnit {
	
	List<Unit>buscarTodas();

}
