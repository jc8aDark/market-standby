package com.iglesia.iglesia.service;

import java.util.List;

import com.iglesia.iglesia.model.Departments;

public interface IDepartments {
	List<Departments> buscarTodas();

	void guardar(Departments departments);

	void eliminar(Integer id);

	Departments buscarPorId(Integer id);
}
