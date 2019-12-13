package com.hcl.traning.rentail.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.traning.rentail.dao.IRentalDao;
import com.hcl.traning.rentail.model.Rental;

@Transactional
@Repository
public class RentalDao implements IRentalDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Rental rental) {
		em.persist(rental);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Rental> findAll() {
		return em.createQuery("from Rental").getResultList();
	}
	
	@Override
	public Rental getById(Long id) {
		
		String hql= "from Rental where id= :id";
        Query query = em.createQuery(hql);
        query.setParameter("id", id);        
	        
		return (Rental) query.getSingleResult();
	}

	@Override
	public void update(Rental rental) {
		em.merge(rental);
		
	}
	
}
