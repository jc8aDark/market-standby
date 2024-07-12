package com.iglesia.iglesia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Suppliers;

@Service
public interface ISuppliers {
	List<Suppliers> buscarTodas();
	void guardar(Suppliers supplier);
	void eliminar(Integer id);
	Suppliers buscarPorId(Integer id);

}
