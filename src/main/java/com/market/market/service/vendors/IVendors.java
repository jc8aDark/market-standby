package com.market.market.service.vendors;

import java.util.List;

import org.springframework.stereotype.Service;

import com.market.market.model.vendors.Vendors;

@Service
public interface IVendors {
	
	List<Vendors>buscarTodas();
	void guardar(Vendors vendor);
	void eliminar(Integer id);
	Vendors buscarPorId(Integer id);
	Vendors buscarPorUsername(String userName);
	
}
