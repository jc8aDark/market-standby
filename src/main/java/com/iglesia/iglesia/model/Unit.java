package com.iglesia.iglesia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Unit")
public class Unit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer Id;
	private String Name;
	private String Slug;
	private String Status;
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
	public String getSlug() {
		return Slug;
	}
	public void setSlug(String slug) {
		Slug = slug;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	@Override
	public String toString() {
		return "Unit [Id=" + Id + ", Name=" + Name + ", Slug=" + Slug + ", Status=" + Status + "]";
	}
	
	
	
	
}
