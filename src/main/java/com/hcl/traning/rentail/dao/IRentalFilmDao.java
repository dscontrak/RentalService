package com.hcl.traning.rentail.dao;

import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.model.RentalFilms;

public interface IRentalFilmDao {

	Rental findAllByRental(Long rentalId);

	void save(RentalFilms rentalFilm);

}