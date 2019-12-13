package com.hcl.traning.rentail.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.model.RentalFilmsSerialize;
import com.hcl.traning.rentail.service.IRentalService;
import com.hcl.traning.rentail.service.impl.RentalFilmService;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	
	@Autowired
	IRentalService service;
	
	@Autowired
	RentalFilmService rentalFilmService; 
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Rental postData(@RequestBody Rental rental) {
		
		service.add(rental);	
		service.addPossiblePayments(rental);
		
		return rental;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Rental> getData() {		
		
		return service.listAll();
	}
	
	@RequestMapping(value = "/{id}/", method = RequestMethod.GET)
	public Rental getDataRentalByID(@PathVariable("id") Long id) {		
		
		return service.getById(id);
	}
	
	@RequestMapping(value = "/{id}/rentalfilms", method = RequestMethod.GET)
	public Set<RentalFilmsSerialize> getDataRentalFilms(@PathVariable("id") Long id) {		
		
		Set<RentalFilmsSerialize> filmsSerializes = new HashSet<RentalFilmsSerialize>();
		Rental rental = rentalFilmService.findAllByRental(id);
		
		rental.getRentalFilms().forEach(rf -> {
			RentalFilmsSerialize rfSerialize = new RentalFilmsSerialize();
			rfSerialize.copyFromRentalFilm(rf);
			
			filmsSerializes.add(rfSerialize);
		});
		
		return filmsSerializes ;
	}
	
	@RequestMapping(value = "/{id}/payments", method = RequestMethod.POST)
	public Rental postDataPayment(@RequestBody Rental rental) {
					
		return service.addPaymentsToRental(rental);
	}
	
	@RequestMapping(value = "/{id}/return", method = RequestMethod.POST)
	public Rental postDataReturn(@RequestBody Rental rental) {
					
		return service.returnRental(rental);
	}
	
}
