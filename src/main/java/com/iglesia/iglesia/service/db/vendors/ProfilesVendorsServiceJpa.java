package com.iglesia.iglesia.service.db.vendors;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.vendors.ProfilesVendors;
import com.iglesia.iglesia.repository.vendors.ProfilesVendorsRepository;
import com.iglesia.iglesia.service.vendors.IProfilesVendors;


@Service
public class ProfilesVendorsServiceJpa implements IProfilesVendors {

	@Autowired 
	private ProfilesVendorsRepository profilVendorRepos;
	
	@Override
	public List<ProfilesVendors> buscarTodas() {
		return profilVendorRepos.findAll();
	}

	@Override
	public ProfilesVendors buscarPorId(Integer id) {
			Optional<ProfilesVendors> optional = profilVendorRepos.findById(id);
			if (optional.isPresent()) {
				return optional.get();
			}
			return null;

	}

}
