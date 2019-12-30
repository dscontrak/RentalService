package com.hcl.traning.rentail.mapper;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class RentalDto {
private Long id;
	
	
	private String code;			
	private CustomerDto customer;	
	@JsonManagedReference(value ="rental-film")
	private Set<RentalFilmsDto> rentalFilms;	
	
	//@JsonManagedReference
	private Set<PaymentDto> payments;		
		
	private Set <PaymentDto> possiblePayments;	
	private String status;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDateTime created;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}		
	
	public CustomerDto getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	
	public Set<RentalFilmsDto> getRentalFilms() {
		return rentalFilms;
	}
	public void setRentalFilms(Set<RentalFilmsDto> rentalFilms) {
		this.rentalFilms = rentalFilms;
	}
	public Set<PaymentDto> getPayments() {
		return payments;
	}
	public void setPayments(Set<PaymentDto> payments) {
		this.payments = payments;
	}
	public Set<PaymentDto> getPossiblePayments() {
		return possiblePayments;
	}
	public void setPossiblePayments(Set<PaymentDto> possiblePayments) {
		this.possiblePayments = possiblePayments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	
	
}
