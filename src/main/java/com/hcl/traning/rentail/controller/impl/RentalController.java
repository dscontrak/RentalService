package com.hcl.traning.rentail.controller.impl;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.traning.rentail.controller.IRentalController;
import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.model.RentalFilmsSerialize;
import com.hcl.traning.rentail.service.IRentalService;
import com.hcl.traning.rentail.service.impl.RentalFilmService;

@RestController
public class RentalController implements IRentalController {
	
	@Autowired
	IRentalService service;
	
	@Autowired
	RentalFilmService rentalFilmService; 
	
	@Override	
	public Rental postData(@RequestBody Rental rental) {		
		return service.add(rental);		
	}
	
	@Override	
	public List<Rental> getData() {				
		return service.listAll();
	}
	
	@Override	
	public Rental getDataRentalByID(@PathVariable("id") Long id) {				
		return service.getById(id);
	}
	
	@Override	
	public Set<RentalFilmsSerialize> getDataRentalFilms(@PathVariable("id") Long id) {				
		return service.getDataRentalFilms(id);
	}
	
	@Override	
	public Rental postDataPayment(@RequestBody Rental rental) {				
		return service.addPaymentsToRental(rental);
	}
	
	@Override	
	public Rental postDataReturn(@RequestBody Rental rental) {					
		return service.returnRental(rental);
	}
	
}
