package com.hcl.traning.rentail.service;

import java.util.List;

import com.hcl.traning.rentail.mapper.CustomerDto;
import com.hcl.traning.rentail.mapper.PaymentDto;
import com.hcl.traning.rentail.mapper.RentalDto;



public interface ICustomerService {

	void add(CustomerDto c);

	//void addAll(Collection<CustomerDto> CustomerDtos);

	List<CustomerDto> listAll();

	CustomerDto getById(Long id);
	
	CustomerDto delete(Long id);

	List<PaymentDto> getPaymentsByCustomerId(Long id);

	List<RentalDto> getRentalsByCustomerId(Long id);

}