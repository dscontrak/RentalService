package com.hcl.traning.rentail.mapper;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class CustomerDto {
	
	private Long id;
	
	private String name;
	private String lastName;
	private String phoneNumber;
	private String address;
	private Integer bonus;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDateTime created;
	
	//@JsonBackReference("rentals")
	private Set<RentalDto> rentals;
	
	//@JsonBackReference("payments")
	private Set<PaymentDto> payments;
	
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
	public Set<RentalDto> getRentals() {
		return rentals;
	}
	public void setRentals(Set<RentalDto> rentals) {
		this.rentals = rentals;
	}
	public Set<PaymentDto> getPayments() {
		return payments;
	}
	public void setPayments(Set<PaymentDto> payments) {
		this.payments = payments;
	}
	
	
		
	
	
	
}
