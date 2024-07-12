package com.market.market.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.market.market.model.Products;


@Service
public interface IProducts {
	List<Products> buscarTodas();
	void guardar(Products product);
	List<Products> buscarByExample(Example<Products> example);
	Products findById(Integer id);
	void eliminar(Integer id);
	

}
