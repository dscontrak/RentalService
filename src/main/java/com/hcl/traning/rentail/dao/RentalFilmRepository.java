package com.hcl.traning.rentail.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.traning.rentail.model.RentalFilms;

@Repository
public interface RentalFilmRepository extends JpaRepository<RentalFilms, Long> {

	@Query("FROM RentalFilms WHERE rental_id = ?1")
	RentalFilms findRentalByRental(Long rentalId);
	
	//Rental findAllByRental(Long rentalId);
	//void save(RentalFilms rentalFilm);	

}