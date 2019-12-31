package com.hcl.traning.rentail.model;


import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;


@Entity
@Table(name = "films")
@SQLDelete(sql ="UPDATE films SET deleted = 1 WHERE id = ?")
@Where(clause = "deleted = 0")
public class Film  extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String title;
	//private String type;	
	private Integer inventoryStore;
	private Integer inventoryRent;
		
	
	private LocalDateTime created;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDateTime updated;
	
	@JsonManagedReference(value ="film-rental")
	//@JsonBackReference(value ="film-rental")
	@OneToMany(mappedBy = "film",  fetch = FetchType.EAGER)
	private Set<RentalFilms> rentalFilms;
	
	@ManyToOne
	@JoinColumn(name ="type_id")
	private TypeFilm typeFilm;
	
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
	public TypeFilm getTypeFilm() {
		return typeFilm;
	}
	public void setTypeFilm(TypeFilm typeFilm) {
		this.typeFilm = typeFilm;
	}
	
	
	
	

	
}
