package com.hcl.traning.rentail.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.model.RentalFilmsSerialize;

public interface IRentalService {

	Rental add(Rental rental);

	void addAll(Collection<Rental> rentals);

	List<Rental> listAll();

	Rental getById(Long id);

	void addPossiblePayments(Rental rental);

	Rental addPaymentsToRental(Rental rentalRequest);

	Rental returnRental(Rental rentalRequest);
	
	Set<RentalFilmsSerialize> getDataRentalFilms(Long id);
	

}