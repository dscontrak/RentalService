package com.hcl.traning.rentail.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.traning.rentail.model.Customer;

@RequestMapping("/api/customers")
public interface ICustomerController {

	@RequestMapping(value = "", method = RequestMethod.POST)
	Customer postData(Customer customer);

	@RequestMapping(value = "", method = RequestMethod.GET)
	List<Customer> getData();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	Customer getById(Long id);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	Customer deleteById(Long id);

}