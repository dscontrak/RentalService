package com.hcl.traning.rentail.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hcl.traning.rentail.controller.impl.CustomerController;
import com.hcl.traning.rentail.mapper.CustomerDto;
import com.hcl.traning.rentail.mapper.PaymentDto;
import com.hcl.traning.rentail.mapper.RentalDto;
import com.hcl.traning.rentail.service.impl.CustomerService;


public class CustomerControllerTest {
			
	@InjectMocks
	CustomerController customerCtrl;
		
	@Mock
	CustomerService customerService;
				
	@Before 
	public void init()
    {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetById() {
		final Long ID = 1l;
		
		CustomerDto customer = new CustomerDto();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		
		when(customerService.getById(ID)).thenReturn(customer);		
		CustomerDto customerFound = customerCtrl.getById(ID);
		
		assertEquals(customerFound.getId() , customer.getId());		
		verify(customerService,times(1)).getById(ID);
		
	}
	
	@Test
	public void testGetData() {
		final Long ID = 1l;
		
		CustomerDto customer = new CustomerDto();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		
		List<CustomerDto> customers = new ArrayList<CustomerDto>();
		customers.add(customer);
		
		when(customerService.listAll()).thenReturn(customers);		
		List<CustomerDto> customersFound = customerCtrl.getData();
						
		verify(customerService,times(1)).listAll();
		assertEquals(customersFound.size(), 1);
		
		
	}
	
	@Test
	public void testPostData() {
		final Long ID = 1l;
		
		CustomerDto customer = new CustomerDto();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		
		CustomerDto customerSaved = customerCtrl.postData(customer);
		
		assertEquals(customerSaved.getId() , customer.getId());
		verify(customerService, times(1)).add(customer);
		
		
	}
	
	@Test
	public void testDeleteById() {
		final Long ID = 1l;
		
		CustomerDto customer = new CustomerDto();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		
		when(customerService.delete(ID)).thenReturn(customer);		
		CustomerDto customerFound = customerCtrl.deleteById(ID);
		
		assertEquals(customerFound.getId() , customer.getId());		
		verify(customerService,times(1)).delete(ID);
		
	}
	
	@Test
	public void getRentalsByCustomerId() {
		final Long ID = 1l;
		List<RentalDto> rentals = new ArrayList<RentalDto>();
		RentalDto rentalDto = new RentalDto();		
		rentals.add(rentalDto);
		
		when(customerService.getRentalsByCustomerId(ID)).thenReturn(rentals);
		
		List<RentalDto> rentalsFound = customerCtrl.getRentalsByCustomerId(ID);
		
		verify(customerService, times(1)).getRentalsByCustomerId(ID);
		assertEquals(rentalsFound.size(), 1);
		
		
	}
	
	@Test
	public void getPaymentsByCustomerId() {
		final Long ID = 1l;
		List<PaymentDto> payments = new ArrayList<PaymentDto>();
		PaymentDto paymentsDto = new PaymentDto();		
		payments.add(paymentsDto);
		
		when(customerService.getPaymentsByCustomerId(ID)).thenReturn(payments);
		
		List<PaymentDto> rentalsFound = customerCtrl.getPaymentsByCustomerId(ID);
		
		verify(customerService, times(1)).getPaymentsByCustomerId(ID);
		assertEquals(rentalsFound.size(), 1);
		
		
	}
	

}
