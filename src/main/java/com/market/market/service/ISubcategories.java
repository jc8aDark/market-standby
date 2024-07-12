package com.market.market.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.market.market.model.Subcategories;

@Service
public interface ISubcategories {
	List<Subcategories> buscarTodas();

	void guardar(Subcategories subcategories);

	void eliminar(Integer id);

	Subcategories buscarPorId(Integer id);
	
	List<Subcategories> obtenerSubcategoriasPorCategoria(Integer idCategoria);
}
