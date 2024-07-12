package com.iglesia.iglesia.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Profiles;
import com.iglesia.iglesia.repository.ProfilesRepository;
import com.iglesia.iglesia.service.IProfiles;

@Service
public class ProfilesServiceJpa implements IProfiles {

	@Autowired
	private ProfilesRepository profilRepos;

	@Override
	public List<Profiles> buscarTodas() {
		return profilRepos.findAll();
	}

	@Override
	public Profiles buscarPorId(Integer id) {
		Optional<Profiles> optional = profilRepos.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
