package com.hcl.traning.rentail.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.traning.rentail.controller.ICustomerController;
import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.service.ICustomerService;


@RestController
public class CustomerController implements ICustomerController {
	
	@Autowired
	ICustomerService service;
	
	@Override	
	public Customer postData(@RequestBody Customer customer) {
		
		service.add(customer);		
		return customer;		
	}
	
	@Override	
	public List<Customer> getData(){
		return service.listAll();
	}
	
	@Override	
	public Customer getById(@PathVariable("id") Long id){
		return service.getById(id);
	}
	
	@Override	
	public Customer deleteById(@PathVariable("id") Long id){
		return service.delete(id);
	}
	
}
