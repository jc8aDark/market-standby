package com.market.market.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String Imagen;
	private String Name;
	private String Description;
	private BigDecimal  Price;
	private Integer Quanty;
	private Integer Taxes;
	private String Status;
	private Integer Discount;
	
	@ManyToOne
	@JoinColumn(name = "SubCategoryId")
	private Subcategories subCategories;
	
	@ManyToOne
	@JoinColumn(name = "CategoryId")
	private Categories categories;
	
	@ManyToOne
	@JoinColumn(name = "StoreId")
	private Stores store;
	
	@ManyToOne
	@JoinColumn(name = "IdTypeProduct")
	private TypeProducts typeProducts;
	
	@ManyToOne
	@JoinColumn(name = "IdUnit")
	private Unit unit;
	
	@ManyToOne
	@JoinColumn(name = "IdSuppliers")
	private Suppliers supplier;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RegistrationDate", updatable = false)
	private Date RegistrationDate;

	public Products() {
		this.RegistrationDate = new Date();
	}

	@PrePersist
	protected void onCreate() {
		if (this.RegistrationDate == null) {
			this.RegistrationDate = new Date();
		}
	}
	
	public void reset() {
		this.Imagen=null;
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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public BigDecimal getPrice() {
		return Price;
	}

	public void setPrice(BigDecimal price) {
		Price = price;
	}

	public Integer getQuanty() {
		return Quanty;
	}

	public void setQuanty(Integer quanty) {
		Quanty = quanty;
	}

	public Integer getTaxes() {
		return Taxes;
	}

	public void setTaxes(Integer taxes) {
		Taxes = taxes;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Integer getDiscount() {
		return Discount;
	}

	public void setDiscount(Integer discount) {
		Discount = discount;
	}

	public Subcategories getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Subcategories subCategories) {
		this.subCategories = subCategories;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Stores getStore() {
		return store;
	}

	public void setStore(Stores store) {
		this.store = store;
	}

	public TypeProducts getTypeProducts() {
		return typeProducts;
	}

	public void setTypeProducts(TypeProducts typeProducts) {
		this.typeProducts = typeProducts;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Suppliers getSupplier() {
		return supplier;
	}

	public void setSupplier(Suppliers supplier) {
		this.supplier = supplier;
	}

	public Date getRegistrationDate() {
		return RegistrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		RegistrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "Products [Id=" + Id + ", Imagen=" + Imagen + ", Name=" + Name + ", Description=" + Description
				+ ", Price=" + Price + ", Quanty=" + Quanty + ", Taxes=" + Taxes + ", Status=" + Status + ", Discount="
				+ Discount + ", subCategories=" + subCategories + ", categories=" + categories + ", store=" + store
				+ ", typeProducts=" + typeProducts + ", unit=" + unit + ", supplier=" + supplier + ", RegistrationDate="
				+ RegistrationDate + "]";
	}

}
