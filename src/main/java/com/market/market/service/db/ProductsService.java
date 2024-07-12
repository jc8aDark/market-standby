package com.market.market.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.market.market.model.Products;
import com.market.market.repository.ProductsRepository;
import com.market.market.service.IProducts;
@Service
public class ProductsService implements IProducts {

	@Autowired
	private ProductsRepository productRepos;
	@Override
	public List<Products> buscarTodas() {
		return productRepos.findAll();
	}

	@Override
	public void guardar(Products product) {
		productRepos.save(product);
	}

	@Override
	public List<Products> buscarByExample(Example<Products> example) {
		return productRepos.findAll(example);
	}

	@Override
	public Products findById(Integer id) {
		 Optional<Products> optionalProduct = productRepos.findById(id);
	      return optionalProduct.orElse(null); // Retorna null si no se encuentra el producto

	}

	@Override
	public void eliminar(Integer id) {
		productRepos.deleteById(id);
		
	}
	
	

}
