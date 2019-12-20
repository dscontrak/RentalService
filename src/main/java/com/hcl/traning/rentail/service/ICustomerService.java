package com.hcl.traning.rentail.service;

import java.util.Collection;
import java.util.List;

import com.hcl.traning.rentail.mapper.CustomerDto;



public interface ICustomerService {

	void add(CustomerDto c);

	void addAll(Collection<CustomerDto> CustomerDtos);

	List<CustomerDto> listAll();

	CustomerDto getById(Long id);
	
	CustomerDto delete(Long id);

}