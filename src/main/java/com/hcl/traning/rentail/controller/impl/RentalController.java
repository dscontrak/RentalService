package com.hcl.traning.rentail.controller.impl;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.traning.rentail.controller.IRentalController;
import com.hcl.traning.rentail.mapper.RentalDto;
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
	public RentalDto postData(@RequestBody RentalDto rental) {		
		return service.add(rental);		
	}
	
	@Override	
	public List<RentalDto> getData() {				
		return service.listAll();
	}
	
	@Override	
	public RentalDto getDataRentalByID(@PathVariable("id") Long id) {	
		
		return service.getById(id);
	}
	
	@Override	
	public Set<RentalFilmsSerialize> getDataRentalFilms(@PathVariable("id") Long id) {				
		return service.getDataRentalFilms(id);
	}
	
	@Override	
	public RentalDto postDataPayment(@RequestBody RentalDto rental) {				
		return service.addPaymentsToRental(rental);
	}
	
	@Override	
	public RentalDto postDataReturn(@RequestBody RentalDto rental) {					
		return service.returnRental(rental);
	}

	
	
}
