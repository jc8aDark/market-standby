package com.iglesia.iglesia.service.vendors;

import java.util.List;
import com.iglesia.iglesia.model.vendors.ProfilesVendors;


public interface IProfilesVendors {
	List<ProfilesVendors> buscarTodas();
	ProfilesVendors buscarPorId(Integer id);

}
