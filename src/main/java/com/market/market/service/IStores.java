package com.market.market.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.market.market.model.Stores;

@Service
public interface IStores {
	List<Stores> buscarTodas();
	void guardar(Stores stores);
}
