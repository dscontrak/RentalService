package com.hcl.traning.rentail.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.traning.rentail.dao.IFilmDao;
import com.hcl.traning.rentail.model.Film;

@Repository
@Transactional
public class FilmDao implements IFilmDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Film film) {
		em.persist(film);
	}
	
	@Override
	public void update(Film film) {
		em.merge(film);
	}
	
	@Override
	public void updateAll(Set<Film> films) {
		films.forEach(f -> { 
				em.merge(f); 
		});
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Film> findAll() {
		return em.createQuery("from Film").getResultList();
	}
	
	@Override
	public Film getById(Long id) {
		
		String hql= "from Film where id= :id";
        Query query = em.createQuery(hql);
        query.setParameter("id", id);        
	        
		return (Film) query.getSingleResult();
	}

	@Override
	public void delete(Film film) {
		//em.remove(film);
		em.remove(em.contains(film) ? film : em.merge(film));	
		
	}
}
