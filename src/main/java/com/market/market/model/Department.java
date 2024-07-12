package com.market.market.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer Id;
	private String Name;
	
	@Column(columnDefinition = "TINYINT")
	private Integer Status;
		
	@ManyToOne
	@JoinColumn(name = "CountryId")
	private Countries countries;

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

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public Countries getCountries() {
		return countries;
	}

	public void setCountries(Countries countries) {
		this.countries = countries;
	}

	@Override
	public String toString() {
		return "Department [Id=" + Id + ", Name=" + Name + ", Status=" + Status + ", countries=" + countries + "]";
	}
		
}
