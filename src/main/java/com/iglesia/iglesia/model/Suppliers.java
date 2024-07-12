package com.iglesia.iglesia.model;

import java.util.Date;

import com.iglesia.iglesia.model.vendors.Vendors;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;


@Entity
@Table(name ="Suppliers")
public class Suppliers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer Id; 
	private String Name;
	private String Email;
	private Integer Dui;
	private String Phone;
	private String Address;
	private String Status;
	private Date RegistrationDate;
	
	@ManyToOne
	@JoinColumn(name = "IdVendor")
	private Vendors vendor;
	
	public Suppliers() {
		this.RegistrationDate = new Date();
	}

	@PrePersist
	protected void onCreate() {
		if (this.RegistrationDate == null) {
			this.RegistrationDate = new Date();
		}
	}

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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Integer getDui() {
		return Dui;
	}

	public void setDui(Integer dui) {
		Dui = dui;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Date getRegistrationDate() {
		return RegistrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		RegistrationDate = registrationDate;
	}

	public Vendors getVendor() {
		return vendor;
	}

	public void setVendor(Vendors vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "Suppliers [Id=" + Id + ", Name=" + Name + ", Email=" + Email + ", Dui=" + Dui + ", Phone=" + Phone
				+ ", Address=" + Address + ", Status=" + Status + ", RegistrationDate=" + RegistrationDate + ", vendor="
				+ vendor + "]";
	}
	
	
	
	
}
