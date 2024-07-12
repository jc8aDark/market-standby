package com.iglesia.iglesia.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Stores;
import com.iglesia.iglesia.repository.StoresRepository;
import com.iglesia.iglesia.service.IStores;

@Service
public class StoresServiceJpa implements IStores {
	
	@Autowired
	private StoresRepository storeRepos;
	
	@Override
	public List<Stores> buscarTodas() {
		return storeRepos.findAll();
	}

	@Override
	public void guardar(Stores stores) {
		storeRepos.save(stores);		
	}

}
