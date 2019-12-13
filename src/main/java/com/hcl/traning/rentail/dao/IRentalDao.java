package com.hcl.traning.rentail.dao;

import java.util.List;

import com.hcl.traning.rentail.model.Rental;

public interface IRentalDao {

	void save(Rental rental);

	List<Rental> findAll();

	Rental getById(Long id);

	void update(Rental rental);

}