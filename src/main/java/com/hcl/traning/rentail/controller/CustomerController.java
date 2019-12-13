package com.hcl.traning.rentail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.service.ICustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	ICustomerService service;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Customer postData(@RequestBody Customer customer) {
		
		service.add(customer);		
		return customer;		
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Customer> getData(){
		return service.listAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Customer getById(@PathVariable("id") Long id){
		return service.getById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Customer deleteById(@PathVariable("id") Long id){
		return service.delete(id);
	}
	
}
