package com.iglesia.iglesia.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Sales")
public class Sales {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer Id;
	private BigDecimal TotalSales;
	private Date RegistrationDate;
	private String Status;
	
	
	@ManyToOne
	@JoinColumn(name = "IdUsers")
	private Users user;
	
	
	@ManyToOne
	@JoinColumn(name = "DeliveryId")
	private Delivery delivery;
	
	@ManyToOne
	@JoinColumn(name = "OrderId")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "IdShippingCost")
	private ShippingCost shippingCost;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public BigDecimal getTotalSales() {
		return TotalSales;
	}

	public void setTotalSales(BigDecimal totalSales) {
		TotalSales = totalSales;
	}

	public Date getRegistrationDate() {
		return RegistrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		RegistrationDate = registrationDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ShippingCost getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(ShippingCost shippingCost) {
		this.shippingCost = shippingCost;
	}

	@Override
	public String toString() {
		return "Sales [Id=" + Id + ", TotalSales=" + TotalSales + ", RegistrationDate=" + RegistrationDate + ", Status="
				+ Status + ", user=" + user + ", delivery=" + delivery + ", order=" + order + ", shippingCost="
				+ shippingCost + "]";
	}

}
