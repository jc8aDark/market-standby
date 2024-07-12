package com.iglesia.iglesia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "TypePayments")
public class TypePayments {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	private Integer Id;
	private String Name;
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
	@Override
	public String toString() {
		return "TypePayments [Id=" + Id + ", Name=" + Name + "]";
	}
	
}
