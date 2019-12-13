package com.hcl.traning.rentail.model;


import java.sql.Timestamp;
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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "rentals")
public class Rental {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String code;	
	
	// Column name same table
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id", nullable = false)
	private Customer customer;
	
	@JsonManagedReference(value ="rental-film")
	@OneToMany(mappedBy = "rental", fetch = FetchType.EAGER)
	private Set<RentalFilms> rentalFilms;
	
	@OneToMany(mappedBy = "rental", fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Payment> payments;
	
	@Transient
	private Set <Payment> possiblePayments;
	
	private String status;
	//private Date returnDate;
	private Timestamp created;
	
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/*public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}*/
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Set<RentalFilms> getRentalFilms() {
		return rentalFilms;
	}
	public void setRentalFilms(Set<RentalFilms> rentalFilms) {
		this.rentalFilms = rentalFilms;
	}
	public Set<Payment> getPayments() {
		return payments;
	}
	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	public Set<Payment> getPossiblePayments() {
		return possiblePayments;
	}
	public void setPossiblePayments(Set<Payment> possiblePayments) {
		this.possiblePayments = possiblePayments;
	}			
		
	
}
