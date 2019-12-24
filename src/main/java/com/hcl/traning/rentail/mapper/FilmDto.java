package com.hcl.traning.rentail.mapper;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class FilmDto {
	private Long id;
	
	private String title;
	//private String type;	
	private Integer inventoryStore;
	private Integer inventoryRent;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDateTime created;
	
	@JsonManagedReference(value ="film-rental")
	private Set<RentalFilmsDto> rentalFilms;
	
	private TypeFilmDto typeFilm;
		
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
	/*public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}*/
	public Integer getInventoryStore() {
		return inventoryStore;
	}
	public void setInventoryStore(Integer inventoryStore) {
		this.inventoryStore = inventoryStore;
	}
	
	
	
	public Set<RentalFilmsDto> getRentalFilms() {
		return rentalFilms;
	}
	public void setRentalFilms(Set<RentalFilmsDto> rentalFilms) {
		this.rentalFilms = rentalFilms;
	}
	public Integer getInventoryRent() {
		return inventoryRent;
	}
	public void setInventoryRent(Integer inventoryRent) {
		this.inventoryRent = inventoryRent;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public TypeFilmDto getTypeFilm() {
		return typeFilm;
	}
	public void setTypeFilm(TypeFilmDto typeFilm) {
		this.typeFilm = typeFilm;
	}
	
	
	
	
	
	
	
	
}
