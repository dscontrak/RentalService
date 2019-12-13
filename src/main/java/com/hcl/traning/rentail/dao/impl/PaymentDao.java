package com.hcl.traning.rentail.dao.impl;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hcl.traning.rentail.dao.IPaymentDao;
import com.hcl.traning.rentail.model.Payment;

@Transactional
@Repository
public class PaymentDao implements IPaymentDao {
	@PersistenceContext
	private EntityManager em;
	
	public void save(Payment payment) {
		em.persist(payment);
	}
	
	public void saveAll(Set<Payment> payment) {
		
		payment.forEach(p -> {
			em.persist(p);
		});
		
	}
}
