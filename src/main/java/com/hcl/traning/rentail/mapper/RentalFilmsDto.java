package com.hcl.traning.rentail.mapper;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope=RentalFilmsDto.class)
public class RentalFilmsDto {
private Long id;

	@JsonBackReference(value ="film-rental")	
	private FilmDto film;
		
	@JsonBackReference(value ="rental-film")
	private RentalDto rental;	
	
	private Integer amount;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate returnWithoutDue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public FilmDto getFilm() {
		return film;
	}

	public void setFilm(FilmDto film) {
		this.film = film;
	}
	
	
	public RentalDto getRental() {
		return rental;
	}

	public void setRental(RentalDto rental) {
		this.rental = rental;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public LocalDate getReturnWithoutDue() {
		return returnWithoutDue;
	}

	public void setReturnWithoutDue(LocalDate returnWithoutDue) {
		this.returnWithoutDue = returnWithoutDue;
	}
	
	
}
