package com.market.market.service.vendors;

import java.util.List;
import com.market.market.model.vendors.ProfilesVendors;


public interface IProfilesVendors {
	List<ProfilesVendors> buscarTodas();
	ProfilesVendors buscarPorId(Integer id);

}
