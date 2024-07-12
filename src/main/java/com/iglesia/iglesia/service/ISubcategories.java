package com.iglesia.iglesia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Subcategories;

@Service
public interface ISubcategories {
	List<Subcategories> buscarTodas();

	void guardar(Subcategories subcategories);

	void eliminar(Integer id);

	Subcategories buscarPorId(Integer id);
	
	List<Subcategories> obtenerSubcategoriasPorCategoria(Integer idCategoria);
}
