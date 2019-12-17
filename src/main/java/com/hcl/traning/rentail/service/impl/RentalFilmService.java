package com.hcl.traning.rentail.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hcl.traning.rentail.dao.RentalFilmRepository;
import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.model.RentalFilms;
import com.hcl.traning.rentail.service.IRentalFilmService;

@Service
public class RentalFilmService implements IRentalFilmService {
	
	@Autowired
	RentalFilmRepository dao;
		
		
	public Rental findAllByRental(Long rentalId) {
		
		RentalFilms rentalOpt = dao.findRentalByRental(rentalId);
		
		//return dao.findAllByRental(rentalId);
		return rentalOpt.getRental();
	}

}
