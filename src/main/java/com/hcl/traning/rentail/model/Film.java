package com.hcl.traning.rentail.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "films")
public class Film {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String title;
	private String type;
	private Timestamp created;
	private Timestamp updated;
	private Integer inventoryStore;
	private Integer inventoryRent;
	
	@JsonManagedReference(value ="film-rental")
	//@JsonBackReference(value ="film-rental")
	@OneToMany(mappedBy = "film",  fetch = FetchType.EAGER)
	private Set<RentalFilms> rentalFilms;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}	
	
	public Integer getInventoryStore() {
		return inventoryStore;
	}
	public void setInventoryStore(Integer inventoryStore) {
		this.inventoryStore = inventoryStore;
	}
	public Integer getInventoryRent() {
		return inventoryRent;
	}
	public void setInventoryRent(Integer inventoryRent) {
		this.inventoryRent = inventoryRent;
	}
	public Set<RentalFilms> getRentalFilms() {
		return rentalFilms;
	}
	public void setRentalFilms(Set<RentalFilms> rentalFilms) {
		this.rentalFilms = rentalFilms;
	}

	
}
