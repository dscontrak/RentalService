package com.hcl.traning.rentail.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.traning.rentail.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
}