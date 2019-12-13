package com.hcl.traning.rentail.service;

import java.util.Collection;
import java.util.List;

import com.hcl.traning.rentail.model.Rental;

public interface IRentalService {

	void add(Rental rental);

	void addAll(Collection<Rental> rentals);

	List<Rental> listAll();

	Rental getById(Long id);

	void addPossiblePayments(Rental rental);

	Rental addPaymentsToRental(Rental rentalRequest);

	Rental returnRental(Rental rentalRequest);

}