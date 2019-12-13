package com.hcl.traning.rentail.service;

import java.util.Collection;
import java.util.List;

import com.hcl.traning.rentail.model.Customer;

public interface ICustomerService {

	void add(Customer c);

	void addAll(Collection<Customer> customers);

	List<Customer> listAll();

	Customer getById(Long id);
	
	Customer delete(Long id);

}