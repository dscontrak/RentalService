package com.hcl.traning.rentail.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.hcl.traning.rentail.mapper.RentalDto;
import com.hcl.traning.rentail.model.RentalFilmsSerialize;

public interface IRentalService {

	RentalDto add(RentalDto rental);

	void addAll(Collection<RentalDto> rentals);

	List<RentalDto> listAll();

	RentalDto getById(Long id);

	void addPossiblePayments(RentalDto rental);

	RentalDto addPaymentsToRental(RentalDto rentalRequest);

	RentalDto returnRental(RentalDto rentalRequest);
	
	Set<RentalFilmsSerialize> getDataRentalFilms(Long id);
	

}