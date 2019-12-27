package com.hcl.traning.rentail.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.BasicConfigurator;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.hcl.traning.rentail.dao.CustomerRepository;
import com.hcl.traning.rentail.mapper.CustomerDto;
import com.hcl.traning.rentail.mapper.PaymentDto;
import com.hcl.traning.rentail.mapper.RentalDto;
import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.model.Payment;
import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.service.impl.CustomerService;


public class CustomerServiceTest {
			
	@InjectMocks
	CustomerService customerService;
		
	@Mock
	CustomerRepository customerDao;
	
	@Spy
	DozerBeanMapper mapper;
				
	@Before 
	public void init()
    {
        MockitoAnnotations.initMocks(this);
        List<String> mappingFiles = new ArrayList<String>();
		mappingFiles.add("dozerJdk8Converters.xml");
		
		mapper = new DozerBeanMapper();	    
		mapper.setMappingFiles(mappingFiles);
		BasicConfigurator.configure();

    }
	
	@Test
	public void testGetById() {
		
		final Long ID = 1l;
		
		// Internal
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		
		
		when(customerDao.findOne(ID)).thenReturn(customer);
		
		// Test
		CustomerDto customerFound = customerService.getById(ID);	
		
		verify(customerDao, times(1)).findOne(ID);
		assertEquals(ID, customerFound.getId());
		assertEquals("Daniel", customerFound.getName());
		assertEquals("Serna", customerFound.getLastName());
		
		
	}
	
	@Test
	public void testListAll() {
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
	public void testAdd() {
		final Long ID = 1l;
		ArgumentCaptor<Customer> argumentAny = ArgumentCaptor.forClass(Customer.class);
		
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
								
		customerService.add(customerDto);		
		
		verify(customerDao).save(argumentAny.capture());
		assertEquals(customerDto.getId(), customer.getId());
		
	}
		
	
	@Test
	public void testDelete() {
		final Long ID = 1l;
		
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setName("Daniel");
		customer.setLastName("Serna");
		
		
		when(customerDao.findOne(ID)).thenReturn(customer);					
		customerService.delete(customer.getId());		
		
		verify(customerDao, times(1)).delete(customer);		
						
	}
	
	@Test
	public void testGetPaymentsByCustomerId() {
		final Long ID = 1l;
		
		
		// Internal
		Customer customer = new Customer();
		customer.setId(ID);
		
		Set<Payment> payments = new HashSet<>();
		Payment payment = new Payment();		
		payments.add(payment);
		customer.setPayments(payments);
		
		when(customerDao.findOne(ID)).thenReturn(customer);
		
		// Test
		List<PaymentDto> paymentsFound = customerService.getPaymentsByCustomerId(ID);					
		
		verify(customerDao, times(1)).findOne(ID);
		assertEquals(paymentsFound.size(), 1);		
		
	}

	@Test
	public void testGetRentalsByCustomerId() {
		final Long ID = 1l;		
		
		// Internal
		Customer customer = new Customer();
		customer.setId(ID);
		
		Set<Rental> rentals = new HashSet<>();
		rentals.add(new Rental());
		customer.setRentals(rentals);
		
		
		when(customerDao.findOne(ID)).thenReturn(customer);
		
		List<RentalDto> rentalsFound = customerService.getRentalsByCustomerId(ID);		
		
		verify(customerDao, times(1)).findOne(ID);
		assertEquals(rentalsFound.size(), 1);
		
	}

}
