package com.iglesia.iglesia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Categories;

@Service
public interface ICategories {
	List<Categories> buscarTodas();

	void guardar(Categories category);

	void eliminar(Integer id);

	Categories buscarPorId(Integer id);

}
