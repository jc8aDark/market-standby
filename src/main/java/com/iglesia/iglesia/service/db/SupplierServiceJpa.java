package com.iglesia.iglesia.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Suppliers;
import com.iglesia.iglesia.repository.SuppliersRepository;
import com.iglesia.iglesia.service.ISuppliers;

@Service
public class SupplierServiceJpa implements ISuppliers {
	
	@Autowired
	private SuppliersRepository suppliersRepos;

	@Override
	public List<Suppliers> buscarTodas() {
		// TODO Auto-generated method stub
		return suppliersRepos.findAll();
	}

	@Override
	public void guardar(Suppliers supplier) {
		// TODO Auto-generated method stub
		suppliersRepos.save(supplier);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		suppliersRepos.deleteById(id);

	}

	@Override
	public Suppliers buscarPorId(Integer id) {
		Optional<Suppliers> optional = suppliersRepos.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
