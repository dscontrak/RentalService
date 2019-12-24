package com.hcl.traning.rentail.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.traning.rentail.controller.ICustomerController;
import com.hcl.traning.rentail.mapper.CustomerDto;
import com.hcl.traning.rentail.mapper.PaymentDto;
import com.hcl.traning.rentail.mapper.RentalDto;
import com.hcl.traning.rentail.service.ICustomerService;


@RestController
public class CustomerController implements ICustomerController {
	
	@Autowired
	ICustomerService service;
	
	@Override	
	public CustomerDto postData(@RequestBody CustomerDto customer) {
		
		service.add(customer);		
		return customer;		
	}
	
	@Override	
	public List<CustomerDto> getData(){
		return service.listAll();
	}
	
	@Override	
	public CustomerDto getById(@PathVariable("id") Long id){
		return service.getById(id);
	}
	
	@Override	
	public CustomerDto deleteById(@PathVariable("id") Long id){
		return service.delete(id);
	}

	@Override
	public List<RentalDto> getRentalsByCustomerId(@PathVariable Long id) {		
		return service.getRentalsByCustomerId(id);
	}

	@Override
	public List<PaymentDto> getPaymentsByCustomerId(@PathVariable Long id) {		
		return service.getPaymentsByCustomerId(id);
	}
	
}
