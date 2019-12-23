package com.hcl.traning.rentail.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.hcl.traning.rentail.dao.CustomerRepository;
import com.hcl.traning.rentail.mapper.CustomerDto;
import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.service.impl.CustomerService;


public class CustomerServiceTest {
			
	@InjectMocks
	CustomerService customerService;
		
	@Mock
	CustomerRepository customerDao;
	
	@Spy
	Mapper mapper;
				
	@Before 
	public void init()
    {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetCustomerById() {
		
		final Long ID = 1l;
		
		// Internal
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		
		
		when(customerDao.findOne(ID)).thenReturn(customer);
		
		// Test
		CustomerDto customerFound = customerService.getById(ID);		
		assertEquals(ID, customerFound.getId());
		assertEquals("Daniel", customerFound.getName());
		assertEquals("Serna", customerFound.getLastName());
		
		
	}
	
	@Test
	public void testGetAll() {
		final Long ID = 1l;
		
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer);
		
		when(customerDao.findAll()).thenReturn( customers );
		
		List<CustomerDto> customersFound = customerService.listAll();
		assertEquals(customersFound.size(), 1);
		verify(customerDao, times(1)).findAll();
						
	}
	
	@Test
	public void testSaveCustomer() {
		final Long ID = 1l;
		
		CustomerDto customer = new CustomerDto();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		
		customerService.add(customer);
		
		verify(customerDao, times(1)).save(new Customer());		
						
	}
		
	
	@Test
	public void testDeleteCustomer() {
		final Long ID = 1l;
		
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		
		
		when(customerDao.findOne(ID)).thenReturn(customer);			
		
		customerService.delete(customer.getId());		
		
		verify(customerDao, times(1)).delete(customer);		
						
	}

}