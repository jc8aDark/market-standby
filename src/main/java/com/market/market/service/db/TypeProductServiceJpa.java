package com.market.market.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.market.model.TypeProducts;
import com.market.market.repository.TypeProductsRepository;
import com.market.market.service.ITypeProducts;


@Service
public class TypeProductServiceJpa implements ITypeProducts {

	@Autowired
	private TypeProductsRepository typeProd;
	
	@Override
	public List<TypeProducts> buscarTodas() {
		return typeProd.findAll();
	}

}
