package com.market.market.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Countries")
public class Countries {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer Id;
	private String Name;
	private String CountryImage;
	private Integer Status;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCountryImage() {
		return CountryImage;
	}
	public void setCountryImage(String countryImage) {
		CountryImage = countryImage;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	
	@Override
	public String toString() {
		return "Countries [Id=" + Id + ", Name=" + Name + ", CountryImage=" + CountryImage + ", Status=" + Status + "]";
	}
	
	
}
