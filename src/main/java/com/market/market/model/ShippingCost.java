package com.market.market.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "ShippingCost")
public class ShippingCost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer Id;
	private Integer Price;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getPrice() {
		return Price;
	}
	public void setPrice(Integer price) {
		Price = price;
	}
	@Override
	public String toString() {
		return "ShippingCost [Id=" + Id + ", Price=" + Price + "]";
	}
	
}
