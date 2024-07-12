package com.iglesia.iglesia.service;

import java.util.List;

import com.iglesia.iglesia.model.Profiles;

public interface IProfiles {
	List<Profiles> buscarTodas();

	Profiles buscarPorId(Integer id);
}
