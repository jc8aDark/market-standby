package com.iglesia.iglesia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TypeProducts")
public class TypeProducts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer Id;
	private String Name;
	private String Description;
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
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	@Override
	public String toString() {
		return "TypeProducts [Id=" + Id + ", Name=" + Name + ", Description=" + Description + ", Status=" + Status
				+ "]";
	}
	
	
	
}
