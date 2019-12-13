package com.hcl.traning.rentail.model;

import java.sql.Date;


/**
 * Use only to serialize a object to prevent Infinite Recursion with Jackson JSON 
 * @author dcontreras
 *
 */
public class RentalFilmsSerialize {
	
	
	private Long id;
	
	
	private Film film;
		
	
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
	
	public void copyFromRentalFilm(RentalFilms original) {
		
		this.id = original.getId();
		this.amount = original.getAmount();
		this.film = original.getFilm();
		this.returnWithoutDue = original.getReturnWithoutDue();
		
	}
	
	
	
}
