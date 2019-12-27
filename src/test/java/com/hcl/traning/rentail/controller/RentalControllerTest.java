package com.hcl.traning.rentail.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.hcl.traning.rentail.controller.impl.RentalController;
import com.hcl.traning.rentail.mapper.CustomerDto;
import com.hcl.traning.rentail.mapper.FilmDto;
import com.hcl.traning.rentail.mapper.PaymentDto;
import com.hcl.traning.rentail.mapper.RentalDto;
import com.hcl.traning.rentail.mapper.RentalFilmsDto;
import com.hcl.traning.rentail.mapper.TypeFilmDto;
import com.hcl.traning.rentail.model.RentalFilmsSerialize;
import com.hcl.traning.rentail.service.impl.RentalService;
import com.hcl.traning.rentail.util.CalcuatePayment;
import com.hcl.traning.rentail.util.CodeGenerator;


public class RentalControllerTest {

	@InjectMocks
	RentalController rentalCtrl;
	
	@Mock
	RentalService rentalService;			
	
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
	public void testPostData() {
		RentalDto rental = new RentalDto();
		//rental.setId(ID);
		
		CustomerDto customer = new CustomerDto();
		customer.setId(1l);
		customer.setBonus(0);
		
		TypeFilmDto typeFilmDto = new TypeFilmDto();
		typeFilmDto.setDaysToReturn(5);
		typeFilmDto.setPrice(30d);
		
		FilmDto film = new FilmDto();
		film.setId(1l);
		film.setTitle("Title");
		film.setTypeFilm(typeFilmDto);
		
		Set<RentalFilmsDto> rentalFilms = new HashSet<>();
		
		RentalFilmsDto rentalFilm = new RentalFilmsDto();
		rentalFilm.setFilm(film);
		rentalFilm.setRental(rental);
		rentalFilm.setId(1l);
		
		rentalFilms.add(rentalFilm);		
		
		rental.setCustomer(customer);
		rental.setRentalFilms(rentalFilms);
		
		RentalDto rentalSaved = rentalCtrl.postData(rental);
				
		assertNull(rentalSaved);										
		verify(rentalService, times(1)).add(rental);
				
	}
	
	@Test
	public void testGetDataRentalById() {
		RentalDto rental = new RentalDto();
		rental.setId(ID);
		rental.setStatus("Q");
		
		CustomerDto customer = new CustomerDto();
		customer.setBonus(0);
		
		TypeFilmDto typeFilmDto = new TypeFilmDto();
		typeFilmDto.setDaysToReturn(5);
		typeFilmDto.setPrice(30d);
		
		FilmDto film = new FilmDto();
		film.setId(1l);
		film.setTitle("Title");
		film.setTypeFilm(typeFilmDto);
		
		Set<RentalFilmsDto> rentalFilms = new HashSet<>();
		
		RentalFilmsDto rentalFilm = new RentalFilmsDto();
		rentalFilm.setFilm(film);
		rentalFilm.setRental(rental);
		rentalFilm.setId(1l);
		
		rentalFilms.add(rentalFilm);
		
		rental.setCustomer(customer);		
		rental.setRentalFilms(rentalFilms);
		
		when(rentalService.getById(ID)).thenReturn(rental);
		
		RentalDto rentalFound = rentalCtrl.getDataRentalByID(ID);
		assertEquals(rentalFound.getId(), ID);
		assertNull(rentalFound.getPossiblePayments());
		verify(rentalService, times(1)).getById(ID);
		
		
	}
	
	@Test
	public void testGetData() {
		RentalDto rental = new RentalDto();
		rental.setId(ID);
		rental.setStatus("Q");
		
		List<RentalDto> rentals = new ArrayList<RentalDto>();
		rentals.add(rental);		
		
		when(rentalService.listAll()).thenReturn(rentals);
		
		List<RentalDto> rentalsFound = rentalCtrl.getData();
		
		verify(rentalService, times(1)).listAll();
		assertEquals(rentalsFound.size(), 1);
		
	}
	
	@Test
	public void testGetDataRentalFilms() {		
		Set<RentalFilmsSerialize> rentalFilms = new HashSet<RentalFilmsSerialize>();
		RentalFilmsSerialize rentalFilm = new RentalFilmsSerialize();
		
		rentalFilms.add(rentalFilm);
		
		when(rentalService.getDataRentalFilms(ID)).thenReturn(rentalFilms);		
		Set<RentalFilmsSerialize> rentalFilmsFound = rentalCtrl.getDataRentalFilms(ID);
		
		verify(rentalService, times(1)).getDataRentalFilms(ID);
		assertEquals(rentalFilmsFound.size(), 1);
	}
	
	@Test
	public void testPostDataPayment() {		
		RentalDto rental = new RentalDto();
		
		Set<PaymentDto> payments = new HashSet<PaymentDto>();
		payments.add(new PaymentDto());
		
		rental.setPayments(payments);
		
		when(rentalService.addPaymentsToRental(rental)).thenReturn(rental);		
		rentalCtrl.postDataPayment(rental);
		
		verify(rentalService, times(1)).addPaymentsToRental(rental);
		
		
	}
	
	@Test
	public void testPostReturn() {		
		RentalDto rental = new RentalDto();
						
		when(rentalService.returnRental(rental)).thenReturn(rental);		
		rentalCtrl.postDataReturn(rental);
		
		verify(rentalService, times(1)).returnRental(rental);		
		
	}
	
	

}
