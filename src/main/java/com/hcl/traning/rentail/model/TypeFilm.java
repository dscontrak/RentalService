package com.hcl.traning.rentail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "typefilms")
@SQLDelete(sql ="UPDATE typefilms SET deleted = 1 WHERE id = ?")
@Where(clause = "deleted = 0")
public class TypeFilm extends BaseEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
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
