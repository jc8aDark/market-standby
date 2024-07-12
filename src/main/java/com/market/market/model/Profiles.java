package com.market.market.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Profiles")
public class Profiles {

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
		return "Profiles [Id=" + Id + ", Profile=" + Profile + "]";
	}

}
