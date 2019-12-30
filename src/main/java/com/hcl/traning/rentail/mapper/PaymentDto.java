package com.hcl.traning.rentail.mapper;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class PaymentDto implements Cloneable{
	private Long id;
	
	private BigDecimal amount;
	private String typePayment;
	private String reasonPayment;
	
	//@JsonBackReference
	private RentalDto rental;		
	private CustomerDto customer;		
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getTypePayment() {
		return typePayment;
	}
	public void setTypePayment(String typePayment) {
		this.typePayment = typePayment;
	}
	public String getReasonPayment() {
		return reasonPayment;
	}
	public void setReasonPayment(String reasonPayment) {
		this.reasonPayment = reasonPayment;
	}		
	
	public RentalDto getRental() {
		return rental;
	}
	public void setRental(RentalDto rental) {
		this.rental = rental;
	}
	public CustomerDto getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {		
		return super.clone();
	}
	
	
}
