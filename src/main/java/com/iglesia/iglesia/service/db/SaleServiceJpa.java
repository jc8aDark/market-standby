package com.iglesia.iglesia.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Sales;
import com.iglesia.iglesia.repository.SalesRepository;
import com.iglesia.iglesia.service.ISales;

@Service
public class SaleServiceJpa implements ISales {
	
	@Autowired
	private SalesRepository salesRepos;

	@Override
	public List<Sales> buscarTodas() {
		return salesRepos.findAll();
	}

	@Override
	public void guardar(Sales sales) {
		salesRepos.save(sales);
	}

	@Override
	public void eliminar(Integer id) {
		salesRepos.deleteById(id);
	}

	@Override
	public Sales buscarPorId(Integer id) {
		 Optional<Sales> optionalOrder = salesRepos.findById(id);
			return optionalOrder.orElse(null);
	}

}
