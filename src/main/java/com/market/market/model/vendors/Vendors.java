package com.market.market.model.vendors;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;

@Entity
@Table(name = "Vendors")
public class Vendors {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer Id;
	private String userName;
	@Column(nullable = false)
	private String Email;
	private String Password;
	
	
	@Column(nullable = false, columnDefinition = "VARCHAR(60) DEFAULT '1'")
	private String Status;
	
	@Column(nullable = false, columnDefinition = "INTEGER(60) DEFAULT 2")
	private Integer ConfirmEmail;
	
	
	 // Relacion ManyToMany con ProfilesVendors
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RolesVendors", // tabla intermedia
            joinColumns = @JoinColumn(name = "VendorsId"), // foreignKey en la tabla de UsuarioPerfil
            inverseJoinColumns = @JoinColumn(name = "ProfilesId") // foreignKey en la tabla de UsuarioPerfil
    )
    private Set<ProfilesVendors> profilesVendors = new HashSet<>();
    
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "RegistrationDate", updatable = false)
		private Date RegistrationDate;
		
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

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getEmail() {
			return Email;
		}

		public void setEmail(String email) {
			Email = email;
		}

		public String getPassword() {
			return Password;
		}

		public void setPassword(String password) {
			Password = password;
		}

		public String getStatus() {
			return Status;
		}

		public void setStatus(String status) {
			Status = status;
		}

		public Integer getConfirmEmail() {
			return ConfirmEmail;
		}

		public void setConfirmEmail(Integer confirmEmail) {
			ConfirmEmail = confirmEmail;
		}

		public Set<ProfilesVendors> getProfilesVendors() {
			return profilesVendors;
		}

		public void setProfilesVendors(Set<ProfilesVendors> profilesVendors) {
			this.profilesVendors = profilesVendors;
		}

		public Date getRegistrationDate() {
			return RegistrationDate;
		}

		public void setRegistrationDate(Date registrationDate) {
			RegistrationDate = registrationDate;
		}

		@Override
		public String toString() {
			return "Vendors [Id=" + Id + ", userName=" + userName + ", Email=" + Email + ", Password=" + Password
					+ ", Status=" + Status + ", ConfirmEmail=" + ConfirmEmail + ", profilesVendors=" + profilesVendors
					+ ", RegistrationDate=" + RegistrationDate + "]";
		}

		public Integer obtenerPerfilId() {
			// TODO Auto-generated method stub
			return 1;
		}
	
		
}
