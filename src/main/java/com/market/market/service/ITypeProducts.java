package com.market.market.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.market.market.model.TypeProducts;

@Service
public interface ITypeProducts {
	List<TypeProducts>buscarTodas();
	
}
