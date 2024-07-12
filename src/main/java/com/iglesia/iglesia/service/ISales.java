package com.iglesia.iglesia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Sales;

@Service
public interface ISales {
	List<Sales> buscarTodas();
	void guardar(Sales sales);
	void eliminar(Integer id);
	Sales buscarPorId(Integer id);

}
