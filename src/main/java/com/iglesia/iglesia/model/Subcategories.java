package com.iglesia.iglesia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SubCategories")
public class Subcategories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer Id;
	private String Name;
	private String Description;

	@ManyToOne
	@JoinColumn(name = "IdCategory")
	private Categories categories;

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

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Subcategories [Id=" + Id + ", Name=" + Name + ", Description=" + Description + ", categories="
				+ categories + "]";
	}

}
