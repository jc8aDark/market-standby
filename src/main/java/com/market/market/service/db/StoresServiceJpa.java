package com.market.market.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.market.model.Stores;
import com.market.market.repository.StoresRepository;
import com.market.market.service.IStores;

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
