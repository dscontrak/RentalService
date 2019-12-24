package com.hcl.traning.rentail.model;


import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hcl.traning.rentail.util.LocalDateTimeConverter;

@Entity
@SQLDelete(sql ="UPDATE customers SET deleted = 1 WHERE id = ?")
@Table(name = "customers", 
uniqueConstraints = { @UniqueConstraint(columnNames ={ "name", "lastName" })})
public class Customer extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String lastName;
	private String phoneNumber;
	private String address;
	private Integer bonus;
	
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime created;
	
	@Convert(converter = LocalDateTimeConverter.class)	
	private LocalDateTime updated;
	
	// Property class the relationship class
	@OneToMany(mappedBy = "customer",  fetch = FetchType.EAGER)	
	@JsonBackReference
	private Set<Rental> rentals;
	
	@OneToMany(mappedBy = "customer",  fetch = FetchType.EAGER)	
	@JsonBackReference
	private Set<Payment> payments;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getBonus() {
		return bonus;
	}
	public void setBonus(Integer bonus) {
		this.bonus = bonus;
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
	
	public Set<Rental> getRentals() {
		return rentals;
	}
	public void setRentals(Set<Rental> rentals) {
		this.rentals = rentals;
	}
	public Set<Payment> getPayments() {
		return payments;
	}
	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	
	
	
	
	
	
}
