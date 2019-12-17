package com.hcl.traning.rentail.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.traning.rentail.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>  {

	
	//void save(Customer customer);

	//List<Customer> findAll();
		
	//Customer getById(Long id);

	//void update(Customer customer);
	
	//void delete(Customer film);

}