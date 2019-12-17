package com.hcl.traning.rentail.servicedao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.hcl.traning.rentail.dao.CustomerRepository;
import com.hcl.traning.rentail.dao.FilmRepository;
import com.hcl.traning.rentail.dao.RentalFilmRepository;
import com.hcl.traning.rentail.dao.RentalRepository;

import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.model.RentalFilms;
import com.hcl.traning.rentail.service.impl.RentalService;
import com.hcl.traning.rentail.util.CalcuatePayment;
import com.hcl.traning.rentail.util.CodeGenerator;


public class MockRental {

	@InjectMocks
	RentalService rentalService;
		
	@Mock
	RentalRepository rentalDao;
	
	@Mock
	RentalFilmRepository daoRentalFilm;
	
	@Mock
	FilmRepository daoFilm;
	
	@Mock
	CustomerRepository daoCustomer;
	
	@Spy 
	CalcuatePayment calcuatePayment;	
	
	@Spy 
	CodeGenerator codeGenerator;
	
	final Long ID = 5l;
	
	@Before 
	public void init()
    {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testSaveRental() {
		Rental rental = new Rental();
		rental.setId(ID);
		
		Customer customer = new Customer();
		customer.setId(1l);
		customer.setBonus(0);
				
		Film film = new Film();
		film.setId(1l);
		film.setTitle("Title");
		film.setType("N");
		
		Set<RentalFilms> rentalFilms = new HashSet<>();
		
		RentalFilms rentalFilm = new RentalFilms();
		rentalFilm.setFilm(film);
		rentalFilm.setRental(rental);
		rentalFilm.setId(1l);
		
		rentalFilms.add(rentalFilm);		
		
		rental.setCustomer(customer);
		rental.setRentalFilms(rentalFilms);
		
		when(daoCustomer.findOne(1l)).thenReturn(customer);
		when(daoFilm.findOne(1l)).thenReturn(film);
		
		rentalService.add(rental);
		
		verify(rentalDao, times(1)).save(rental);
		verify(daoCustomer, times(1)).findOne(1l);
		verify(daoFilm, times(1)).findOne(1l);
				
		
	}
	
	@Test
	public void testGetRentalById() {
		Rental rental = new Rental();
		rental.setId(ID);
		rental.setStatus("Q");
		
		Customer customer = new Customer();
		customer.setBonus(0);
				
		Film film = new Film();
		film.setId(1l);
		film.setTitle("Title");
		film.setType("N");
		
		Set<RentalFilms> rentalFilms = new HashSet<>();
		
		RentalFilms rentalFilm = new RentalFilms();
		rentalFilm.setFilm(film);
		rentalFilm.setRental(rental);
		rentalFilm.setId(1l);
		
		rentalFilms.add(rentalFilm);
		
		rental.setCustomer(customer);		
		rental.setRentalFilms(rentalFilms);
		
		when(rentalDao.findOne(ID)).thenReturn(rental);
		
		Rental rentalFound = rentalService.getById(ID);
		assertEquals(rentalFound.getId(), ID);
		assertEquals(rentalFound.getPossiblePayments().size(), 1);
		verify(rentalDao, times(1)).findOne(ID);
		
		
	}
	
	

}
