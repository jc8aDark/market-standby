package com.iglesia.iglesia.service.vendors;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.vendors.Vendors;

@Service
public interface IVendors {
	
	List<Vendors>buscarTodas();
	void guardar(Vendors vendor);
	void eliminar(Integer id);
	Vendors buscarPorId(Integer id);
	Vendors buscarPorUsername(String userName);
	
}
