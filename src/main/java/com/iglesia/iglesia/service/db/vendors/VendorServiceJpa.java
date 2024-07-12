package com.iglesia.iglesia.service.db.vendors;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.vendors.Vendors;
import com.iglesia.iglesia.repository.vendors.VendorsRepository;
import com.iglesia.iglesia.service.vendors.IVendors;

@Service
public class VendorServiceJpa implements IVendors {

	@Autowired 
	VendorsRepository vendorRepos;
	
	@Override
	public List<Vendors> buscarTodas() {
		return vendorRepos.findAll() ;
	}

	@Override
	public void guardar(Vendors vendor) {
		vendorRepos.save(vendor);
	}

	@Override
	public Vendors buscarPorId(Integer id) {
		Optional<Vendors> optional = vendorRepos.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Override
	public void eliminar(Integer id) {
		vendorRepos.deleteById(id);
	}

	@Override
	public Vendors buscarPorUsername(String userName) {
		return vendorRepos.findByUserName(userName);
	}
	

}
