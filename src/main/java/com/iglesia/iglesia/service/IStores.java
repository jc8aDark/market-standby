package com.iglesia.iglesia.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.iglesia.iglesia.model.Stores;

@Service
public interface IStores {
	List<Stores> buscarTodas();
	void guardar(Stores stores);
}
