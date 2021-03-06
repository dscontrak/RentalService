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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.hcl.traning.rentail.dao.CustomerRepository;
import com.hcl.traning.rentail.dao.FilmRepository;
import com.hcl.traning.rentail.dao.RentalFilmRepository;
import com.hcl.traning.rentail.dao.RentalRepository;
import com.hcl.traning.rentail.mapper.CustomerDto;
import com.hcl.traning.rentail.mapper.FilmDto;
import com.hcl.traning.rentail.mapper.RentalDto;
import com.hcl.traning.rentail.mapper.RentalFilmsDto;
import com.hcl.traning.rentail.mapper.TypeFilmDto;
import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.model.RentalFilms;
import com.hcl.traning.rentail.model.TypeFilm;
import com.hcl.traning.rentail.service.impl.RentalService;
import com.hcl.traning.rentail.util.CalcuatePayment;
import com.hcl.traning.rentail.util.CodeGenerator;


public class RentalServiceTest {

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
	
	@Spy
	DozerBeanMapper mapper;
	
	final Long ID = 5l;
	
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
	
	//@Test // Problem with dozer library
	public void testAdd() {
		RentalDto rental = new RentalDto();
		rental.setId(ID);
		
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
		
		when(daoCustomer.findOne(1l)).thenReturn(mapper.map(customer, Customer.class));
		when(daoFilm.findOne(1l)).thenReturn(mapper.map(film, Film.class));
		
		rentalService.add(rental);
		
		verify(rentalDao, times(1)).save(new Rental());
		verify(daoCustomer, times(1)).findOne(1l);
		verify(daoFilm, times(1)).findOne(1l);
				
		
	}
	
	@Test
	public void testGetById() {
		Rental rental = new Rental();
		rental.setId(ID);
		rental.setStatus("Q");
		
		Customer customer = new Customer();
		customer.setBonus(0);
		
		TypeFilm typeFilmDto = new TypeFilm();
		typeFilmDto.setDaysToReturn(5);
		typeFilmDto.setPrice(30d);
				
		Film film = new Film();
		film.setId(1l);
		film.setTitle("Title");
		film.setTypeFilm(typeFilmDto);
		
		Set<RentalFilms> rentalFilms = new HashSet<>();
		
		RentalFilms rentalFilm = new RentalFilms();
		rentalFilm.setFilm(film);
		rentalFilm.setRental(rental);
		rentalFilm.setId(1l);
		
		rentalFilms.add(rentalFilm);
		
		rental.setCustomer(customer);		
		rental.setRentalFilms(rentalFilms);
		
		when(rentalDao.findOne(ID)).thenReturn(rental);
		
		RentalDto rentalFound = rentalService.getById(ID);
		assertEquals(rentalFound.getId(), ID);
		assertEquals(rentalFound.getPossiblePayments().size(), 1);
		verify(rentalDao, times(1)).findOne(ID);
		
		
	}
	
	

}
