package com.market.market.service;

import java.util.List;

import com.market.market.model.Profiles;

public interface IProfiles {
	List<Profiles> buscarTodas();

	Profiles buscarPorId(Integer id);
}
