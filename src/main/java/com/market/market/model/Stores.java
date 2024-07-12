package com.market.market.model;

import java.util.Date;

import com.market.market.model.vendors.Vendors;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "Stores")

public class Stores {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Integer Id;
private String Name;
private String Logo;
private String About;
private String Email;
private String Address;
private String Phone;
private String CodeVendor;
private Date DateCreate;
private Date DateUpdate;
private Integer Verification;
private Integer Status;

@ManyToOne
@JoinColumn(name = "CountryId")
private Countries countries;

@ManyToOne
@JoinColumn(name = "DepartmentId")
private Department department;

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

public Integer getId() {
	return Id;
}

public String getAbout() {
	return About;
}

public void setAbout(String about) {
	About = about;
}

public Vendors getVendor() {
	return vendor;
}

public void setVendor(Vendors vendor) {
	this.vendor = vendor;
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

public String getLogo() {
	return Logo;
}

public void setLogo(String logo) {
	Logo = logo;
}

public String getEmail() {
	return Email;
}

public void setEmail(String email) {
	Email = email;
}

public String getAddress() {
	return Address;
}

public void setAddress(String address) {
	Address = address;
}

public String getPhone() {
	return Phone;
}

public void setPhone(String phone) {
	Phone = phone;
}

public String getCodeVendor() {
	return CodeVendor;
}

public void setCodeVendor(String codeVendor) {
	CodeVendor = codeVendor;
}

public Date getDateCreate() {
	return DateCreate;
}

public void setDateCreate(Date dateCreate) {
	DateCreate = dateCreate;
}

public Date getDateUpdate() {
	return DateUpdate;
}

public void setDateUpdate(Date dateUpdate) {
	DateUpdate = dateUpdate;
}

public Integer getVerification() {
	return Verification;
}

public void setVerification(Integer verification) {
	Verification = verification;
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

public Department getDepartment() {
	return department;
}

public void setDepartment(Department department) {
	this.department = department;
}

@Override
public String toString() {
	return "Stores [Id=" + Id + ", Name=" + Name + ", Logo=" + Logo + ", About=" + About + ", Email=" + Email
			+ ", Address=" + Address + ", Phone=" + Phone + ", CodeVendor=" + CodeVendor + ", DateCreate=" + DateCreate
			+ ", DateUpdate=" + DateUpdate + ", Verification=" + Verification + ", Status=" + Status + ", countries="
			+ countries + ", department=" + department + ", vendor=" + vendor + "]";
}



}
