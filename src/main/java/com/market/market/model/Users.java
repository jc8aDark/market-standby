package com.market.market.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.market.market.model.vendors.Vendors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String Imagen;
	private String Name;
	private String LastName;
	private String userName;
	private String Password;
	private String Phone;
	private String Address;
	private String Email;
	private Integer Dui;
	private Date Birthday;

	@Column(nullable = false, columnDefinition = "VARCHAR(60) DEFAULT '1'")
	private String Status;

	// Relacion ManyToMany (Un usuario tiene muchos perfiles)
	// Por defecto Fetch es FetchType.LAZY
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Roles", // tabla intermedia
			joinColumns = @JoinColumn(name = "UsersId"), // foreignKey en la tabla de UsuarioPerfil
			inverseJoinColumns = @JoinColumn(name = "ProfilesId") // foreignKey en la tabla de UsuarioPerfil
	)
	
	
private List<Profiles> perfiles;
	
	// Metodo para agregar perfiles
	public void agregar(Profiles tempPerfil) {
		if (perfiles == null) {
			perfiles = new LinkedList<>();
		}
		perfiles.add(tempPerfil);
	}
	
	
	@ManyToOne
	@JoinColumn(name = "IdProfile")
	private Profiles profiles;
	

	@ManyToOne
	@JoinColumn(name = "IdDepartments")
	private Departments departments;
	
	@ManyToOne
	@JoinColumn(name = "IdVendor")
	private Vendors vendor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RegistrationDate", updatable = false)
	private Date RegistrationDate;

	public Users() {
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
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

	public Date getBirthday() {
		return Birthday;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public List<Profiles> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Profiles> perfiles) {
		this.perfiles = perfiles;
	}

	public Profiles getProfiles() {
		return profiles;
	}

	public void setProfiles(Profiles profiles) {
		this.profiles = profiles;
	}

	public Departments getDepartments() {
		return departments;
	}

	public void setDepartments(Departments departments) {
		this.departments = departments;
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
		return "Users [Id=" + Id + ", Imagen=" + Imagen + ", Name=" + Name + ", LastName=" + LastName + ", userName="
				+ userName + ", Password=" + Password + ", Phone=" + Phone + ", Address=" + Address + ", Email=" + Email
				+ ", Dui=" + Dui + ", Birthday=" + Birthday + ", Status=" + Status + ", perfiles=" + perfiles
				+ ", profiles=" + profiles + ", departments=" + departments + ", vendor=" + vendor
				+ ", RegistrationDate=" + RegistrationDate + "]";
	}

	
	

	
}
