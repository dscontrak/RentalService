package com.hcl.traning.rentail.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "rental_film")
//@JsonInclude(value = Include.NON_NULL)
public class RentalFilms {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference(value ="film-rental")
	//@JsonManagedReference(value ="film-rental")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="film_id")		
	private Film film;
	
	@JsonBackReference(value ="rental-film")
	//@JsonManagedReference(value ="rental-film")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rental_id")
	private Rental rental;
	
	// Additional field
	private Integer amount;
	
	private Date returnWithoutDue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getReturnWithoutDue() {
		return returnWithoutDue;
	}

	public void setReturnWithoutDue(Date returnWithoutDue) {
		this.returnWithoutDue = returnWithoutDue;
	}
	
	
	
	
	
}
