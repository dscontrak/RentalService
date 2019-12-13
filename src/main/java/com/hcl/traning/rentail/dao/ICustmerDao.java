package com.hcl.traning.rentail.dao;

import java.util.List;

import com.hcl.traning.rentail.model.Customer;

public interface ICustmerDao {

	void save(Customer customer);

	List<Customer> findAll();

	Customer getById(Long id);

	void update(Customer customer);
	
	void delete(Customer film);

}