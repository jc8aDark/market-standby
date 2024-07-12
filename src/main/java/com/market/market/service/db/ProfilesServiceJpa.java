package com.market.market.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.market.model.Profiles;
import com.market.market.repository.ProfilesRepository;
import com.market.market.service.IProfiles;

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
