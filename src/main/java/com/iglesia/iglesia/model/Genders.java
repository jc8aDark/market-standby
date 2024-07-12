package com.iglesia.iglesia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Genders")
public class Genders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer Id;
	//private String Name;
	
	
}
