package com.iglesia.iglesia.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.TypeProducts;
import com.iglesia.iglesia.repository.TypeProductsRepository;
import com.iglesia.iglesia.service.ITypeProducts;


@Service
public class TypeProductServiceJpa implements ITypeProducts {

	@Autowired
	private TypeProductsRepository typeProd;
	
	@Override
	public List<TypeProducts> buscarTodas() {
		return typeProd.findAll();
	}

}
