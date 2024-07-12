package com.market.market.model;

import java.math.BigDecimal;
import java.sql.Date;

import com.market.market.model.vendors.Vendors;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name ="Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer Id;
	private String Name;
	private Integer Quantity =0;
	private BigDecimal Price;
	private BigDecimal Subtotal;
	private Date RegistrationOrderDate;
	private String Status = "1";
	 public Order() {
	        this.Quantity = 0; // Puedes inicializarlo con el valor que desees
	        this.Status = "1";
	        this.idProduct = new Products();
	        this.RegistrationOrderDate = new Date(System.currentTimeMillis()); // Asigna la fecha y hora actual al momento de crear la orden
	 }
	
	
	@ManyToOne
	@JoinColumn(name = "IdProduct")
	private Products idProduct;
	
	@ManyToOne
	@JoinColumn(name = "IdVendor")
	private Vendors vendor;
	
	public Integer obtenerIdVendor() {
	    if (vendor != null) {
	        return vendor.getId(); // Retorna el ID del vendor asociado al producto
	    } else {
	        return null; // Retorna null si el producto no tiene un vendor asociado
	    }
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public BigDecimal getPrice() {
		return Price;
	}

	public void setPrice(BigDecimal price) {
		Price = price;
	}

	public BigDecimal getSubtotal() {
		return Subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		Subtotal = subtotal;
	}

	public Date getRegistrationOrderDate() {
		return RegistrationOrderDate;
	}

	public void setRegistrationOrderDate(Date registrationOrderDate) {
		RegistrationOrderDate = registrationOrderDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Products getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Products idProduct) {
		this.idProduct = idProduct;
	}

	public Vendors getVendor() {
		return vendor;
	}

	public void setVendor(Vendors vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "Order [Id=" + Id + ", Name=" + Name + ", Quantity=" + Quantity + ", Price=" + Price + ", Subtotal="
				+ Subtotal + ", RegistrationOrderDate=" + RegistrationOrderDate + ", Status=" + Status + ", idProduct="
				+ idProduct + ", vendor=" + vendor + "]";
	}
	
}
