package com.hcl.traning.rentail.dao.impl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.traning.rentail.dao.IRentalFilmDao;
import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.model.RentalFilms;

@Repository
@Transactional
public class RentalFilmDao implements IRentalFilmDao {
	@PersistenceContext
	private EntityManager em;
		
	@Override
	public Rental findAllByRental(Long rentalId) {
		
		Query query = em.createQuery("from Rental r WHERE r.id = :rental");
		query.setParameter("rental", rentalId);
		
		return (Rental)query.getSingleResult();
		
	}
	
	@Override
	public void save(RentalFilms rentalFilm) {
		em.persist(rentalFilm);
	}
}
