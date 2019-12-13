package com.hcl.traning.rentail.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.model.RentalFilmsSerialize;

@RequestMapping("/api/rentals")
public interface IRentalController {

	@RequestMapping(value = "", method = RequestMethod.POST)
	Rental postData(Rental rental);

	@RequestMapping(value = "", method = RequestMethod.GET)
	List<Rental> getData();

	@RequestMapping(value = "/{id}/", method = RequestMethod.GET)
	Rental getDataRentalByID(Long id);

	@RequestMapping(value = "/{id}/rentalfilms", method = RequestMethod.GET)
	Set<RentalFilmsSerialize> getDataRentalFilms(Long id);
	
	@RequestMapping(value = "/{id}/payments", method = RequestMethod.POST)
	Rental postDataPayment(Rental rental);

	@RequestMapping(value = "/{id}/return", method = RequestMethod.POST)
	Rental postDataReturn(Rental rental);

}