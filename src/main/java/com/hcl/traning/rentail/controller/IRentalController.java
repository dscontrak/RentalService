package com.hcl.traning.rentail.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.traning.rentail.mapper.RentalDto;
import com.hcl.traning.rentail.model.RentalFilmsSerialize;

@RequestMapping("/api/rentals")
public interface IRentalController {

	@RequestMapping(value = "", method = RequestMethod.POST)
	RentalDto postData(RentalDto rental);

	@RequestMapping(value = "", method = RequestMethod.GET)
	List<RentalDto> getData();

	@RequestMapping(value = "/{id}/", method = RequestMethod.GET)
	RentalDto getDataRentalByID(Long id);

	@RequestMapping(value = "/{id}/rentalfilms", method = RequestMethod.GET)
	Set<RentalFilmsSerialize> getDataRentalFilms(Long id);
	
	@RequestMapping(value = "/{id}/payments", method = RequestMethod.POST, consumes = "application/json")
	RentalDto postDataPayment(RentalDto rental);

	@RequestMapping(value = "/{id}/return", method = RequestMethod.POST, consumes = "application/json")
	RentalDto postDataReturn(RentalDto rental);

}