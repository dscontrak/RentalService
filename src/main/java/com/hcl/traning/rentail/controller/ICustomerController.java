package com.hcl.traning.rentail.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.traning.rentail.mapper.CustomerDto;



@RequestMapping("/api/customers")
public interface ICustomerController {

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
	CustomerDto postData(CustomerDto customer);

	@RequestMapping(value = "", method = RequestMethod.GET)
	List<CustomerDto> getData();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	CustomerDto getById(Long id);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	CustomerDto deleteById(Long id);

}