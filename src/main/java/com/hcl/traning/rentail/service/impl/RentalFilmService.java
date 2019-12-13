package com.hcl.traning.rentail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.traning.rentail.dao.IRentalFilmDao;
import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.service.IRentalFilmService;

@Service
public class RentalFilmService implements IRentalFilmService {
	
	@Autowired
	IRentalFilmDao dao;
		
		
	public Rental findAllByRental(Long rentalId) {
		
		return dao.findAllByRental(rentalId);	
	}

}
