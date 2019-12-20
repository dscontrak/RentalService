package com.hcl.traning.rentail.service.impl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.traning.rentail.dao.CustomerRepository;
import com.hcl.traning.rentail.mapper.CustomerDto;
import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {
	
	@Autowired
	private CustomerRepository dao;	
	
	@Autowired
	@Qualifier("org.dozer.Mapper")
	Mapper mapper;
	
	@Override
	public void add(CustomerDto cDto) {
		Customer c = mapper.map(cDto, Customer.class);
		
		LocalDateTime timestamp = LocalDateTime.now();
		c.setBonus(0);
		c.setUpdated(timestamp);
		c.setCreated(timestamp);
		
		dao.save(c);
		cDto.setId(c.getId());
	}	
	
	@Override
	public void addAll(Collection<CustomerDto> customers) {
		LocalDateTime timestamp = LocalDateTime.now();
		
		customers.forEach(cDto -> {
			Customer c = mapper.map(cDto, Customer.class);
			c.setBonus(0);
			c.setUpdated(timestamp);
			c.setCreated(timestamp);
			dao.save(c);
		});
				
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CustomerDto> listAll() {
		List<CustomerDto> customers = new ArrayList<CustomerDto>();
		
		dao.findAll().forEach(c -> {
			customers.add(mapper.map(c, CustomerDto.class));
		});
		
		return customers;
	}
	
	@Override
	public CustomerDto getById(Long id) {
						
		return mapper.map( dao.findOne(id) , CustomerDto.class );
	}

	@Override
	public CustomerDto delete(Long id) {
		
		Customer customer = dao.findOne(id);;
		
		if(customer == null) {
			throw new IllegalArgumentException("Not found the Customer in Data Base");
		}
		
		dao.delete(customer);
				
		return mapper.map(customer, CustomerDto.class);
		
	}
}
