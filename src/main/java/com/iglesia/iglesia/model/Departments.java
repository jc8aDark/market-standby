package com.iglesia.iglesia.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Departments")

public class Departments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer Id;
	private String Name;
	private String Description;
	private String Status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created", updatable = false)
	private Date Created;

	public Departments() {
		this.Created = new Date();
	}

	@PrePersist
	protected void onCreate() {
		if (this.Created == null) {
			this.Created = new Date();
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

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		Created = created;
	}

	@Override
	public String toString() {
		return "Departments [Id=" + Id + ", Name=" + Name + ", Description=" + Description + ", Status=" + Status
				+ ", Created=" + Created + "]";
	}

}
