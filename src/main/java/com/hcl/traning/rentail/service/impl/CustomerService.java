package com.hcl.traning.rentail.service.impl;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.traning.rentail.dao.ICustomerDao;
import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {
	
	@Autowired
	private ICustomerDao dao;	
	
	@Override
	public void add(Customer c) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		c.setBonus(0);
		c.setUpdated(timestamp);
		c.setCreated(timestamp);
		dao.save(c);
	}	
	
	@Override
	public void addAll(Collection<Customer> customers) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
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
		return dao.getById(id);
	}

	@Override
	public Customer delete(Long id) {
		
		Customer customer = dao.getById(id);
		
		if(customer == null) {
			throw new IllegalArgumentException("Not found the Customer in Data Nase");
		}
		
		dao.delete(customer);		
		return customer;
		
	}
}
