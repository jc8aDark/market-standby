package com.market.market.service;

import java.util.List;

import com.market.market.model.Departments;

public interface IDepartments {
	List<Departments> buscarTodas();

	void guardar(Departments departments);

	void eliminar(Integer id);

	Departments buscarPorId(Integer id);
}
