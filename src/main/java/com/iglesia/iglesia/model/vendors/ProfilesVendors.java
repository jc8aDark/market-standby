package com.iglesia.iglesia.model.vendors;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "ProfilesVendors")
public class ProfilesVendors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer Id;
	private String Profile;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getProfile() {
		return Profile;
	}
	public void setProfile(String profile) {
		Profile = profile;
	}
	
	@Override
	public String toString() {
		return "ProfilesVendors [Id=" + Id + ", Profile=" + Profile + "]";
	}
	
	

}
