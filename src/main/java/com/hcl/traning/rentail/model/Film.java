package com.hcl.traning.rentail.model;


import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hcl.traning.rentail.util.LocalDateTimeConverter;

@Entity
@Table(name = "films")
public class Film {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String title;
	private String type;	
	private Integer inventoryStore;
	private Integer inventoryRent;
		
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime created;
	
	
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime updated;
	
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
	
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	
	public LocalDateTime getUpdated() {
		return updated;
	}
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}
	
	

	
}
