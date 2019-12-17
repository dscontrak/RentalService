package com.hcl.traning.rentail.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.traning.rentail.dao.ICustomerDao;
import com.hcl.traning.rentail.model.Customer;


@Transactional
@Repository
public class CustomerDao implements ICustomerDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Customer customer) {
		em.persist(customer);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> findAll() {
		return em.createQuery("from Customer").getResultList();
	}
	
	@Override
	public Customer getById(Long id) {
		
		String hql= "from Customer where id= :id";
        Query query = em.createQuery(hql);
        query.setParameter("id", id);        
	        
		return (Customer) query.getSingleResult();
	}

	@Override
	public void update(Customer customer) {
		em.merge(customer);
		
	}

	@Override
	public void delete(Customer customer) {
		em.remove(em.contains(customer) ? customer : em.merge(customer));	
		
	}
	
}
