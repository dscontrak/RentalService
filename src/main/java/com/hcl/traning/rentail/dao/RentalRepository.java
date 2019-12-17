package com.hcl.traning.rentail.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.traning.rentail.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long>{

	/*void save(Rental rental);
	List<Rental> findAll();
	Rental getById(Long id);
	void update(Rental rental);*/

}