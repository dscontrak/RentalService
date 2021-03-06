package com.hcl.traning.rentail.mapper;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class TypeFilmDto {
	
	private Long id;
	
	
	private String name;
	
	private Double price;
	private Integer daysToReturn;
	
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getDaysToReturn() {
		return daysToReturn;
	}
	public void setDaysToReturn(Integer daysToReturn) {
		this.daysToReturn = daysToReturn;
	}
	
	
	
}
