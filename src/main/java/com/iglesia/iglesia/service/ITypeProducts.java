package com.iglesia.iglesia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.TypeProducts;

@Service
public interface ITypeProducts {
	List<TypeProducts>buscarTodas();
	
}
