package com.market.market.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.market.market.model.Categories;

@Service
public interface ICategories {
	List<Categories> buscarTodas();

	void guardar(Categories category);

	void eliminar(Integer id);

	Categories buscarPorId(Integer id);

}
