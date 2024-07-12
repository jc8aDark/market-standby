package com.market.market.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name="ProfilDeliverys")

public class ProfilDeliverys {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer Id;

	private String Imagen;
	private String Name;
	private String LastName;
	private Integer Dui;
	private Integer License;
	private String Phone;
	private String Address;
	private Date Birthday;
	
	@ManyToOne
	@JoinColumn(name = "CountryId")
	private Countries countrie;
	
	@ManyToOne
	@JoinColumn(name = "DepartmentId")
	private Departments department;
	
	@ManyToOne
	@JoinColumn(name = "GenderId")
	private Genders gender;
	
	@ManyToOne
	@JoinColumn(name = "MaritalStatusId")
	private MaritalStatus maritalStatus;
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getImagen() {
		return Imagen;
	}

	public void setImagen(String imagen) {
		Imagen = imagen;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Integer getDui() {
		return Dui;
	}

	public void setDui(Integer dui) {
		Dui = dui;
	}

	public Integer getLicense() {
		return License;
	}

	public void setLicense(Integer license) {
		License = license;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAdress() {
		return Address;
	}

	public void setAdress(String adress) {
		Address = adress;
	}

	public Date getBirthday() {
		return Birthday;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	public Countries getCountrie() {
		return countrie;
	}

	public void setCountrie(Countries countrie) {
		this.countrie = countrie;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public Genders getGender() {
		return gender;
	}

	public void setGender(Genders gender) {
		this.gender = gender;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Override
	public String toString() {
		return "ProfilDeliverys [Id=" + Id + ", Imagen=" + Imagen + ", Name=" + Name + ", LastName=" + LastName
				+ ", Dui=" + Dui + ", License=" + License + ", Phone=" + Phone + ", Address=" + Address + ", Birthday="
				+ Birthday + ", countrie=" + countrie + ", department=" + department + ", gender=" + gender
				+ ", maritalStatus=" + maritalStatus + "]";
	}


	
}
