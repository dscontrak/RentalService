package com.hcl.traning.rentail.controllerservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hcl.traning.rentail.controller.impl.CustomerController;
import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.service.impl.CustomerService;


public class MockCustomer {
			
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
	public void testGetCustomerById() {
		final Long ID = 1l;
		
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		
		when(customerService.getById(ID)).thenReturn(customer);		
		Customer customerFound = customerCtrl.getById(ID);
		
		assertEquals(customerFound.getId() , customer.getId());		
		verify(customerService,times(1)).getById(ID);
		
	}
	
	@Test
	public void testSaveCustomer() {
		final Long ID = 1l;
		
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		
		Customer customerSaved = customerCtrl.postData(customer);
		
		assertEquals(customerSaved.getId() , customer.getId());
		verify(customerService, times(1)).add(customer);
		
		
	}
	

}