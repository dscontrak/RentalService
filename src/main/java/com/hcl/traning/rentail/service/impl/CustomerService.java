package com.hcl.traning.rentail.service.impl;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.traning.rentail.dao.CustomerRepository;
import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {
	
	@Autowired
	private CustomerRepository dao;	
	
	@Override
	public void add(Customer c) {
		LocalDateTime timestamp = LocalDateTime.now();
		c.setBonus(0);
		c.setUpdated(timestamp);
		c.setCreated(timestamp);
		dao.save(c);
	}	
	
	@Override
	public void addAll(Collection<Customer> customers) {
		LocalDateTime timestamp = LocalDateTime.now();
		
		customers.forEach(c -> {
			c.setBonus(0);
			c.setUpdated(timestamp);
			c.setCreated(timestamp);
			dao.save(c);
		});
				
	}
	
	@Override
	public List<Customer> listAll() {
		return dao.findAll();
	}
	
	@Override
	public Customer getById(Long id) {
		
		
		
		return dao.findOne(id);
	}

	@Override
	public Customer delete(Long id) {
		
		Customer customer = dao.findOne(id);;
		
		if(customer == null) {
			throw new IllegalArgumentException("Not found the Customer in Data Base");
		}
		
		dao.delete(customer);
				
		return customer;
		
	}
}
