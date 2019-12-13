package com.hcl.traning.rentail.service.impl;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.traning.rentail.dao.IFilmDao;
import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.service.IFilmService;

@Service
public class FilmService implements IFilmService {
	
	@Autowired
	private IFilmDao dao;	
	
	@Override
	public void add(Film film) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		film.setUpdated(timestamp);
		film.setCreated(timestamp);
		dao.save(film);
	}	
	
	@Override
	public void addAll(Collection<Film> films) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		films.forEach(f ->{
			f.setUpdated(timestamp);
			f.setCreated(timestamp);
			dao.save(f);
		});
				
	}
	
	@Override
	public List<Film> listAll() {
		return dao.findAll();

	}

	@Override
	public Film delete(Long id) {
		
		Film film = dao.getById(id);
		
		if(film == null) {
			throw new IllegalArgumentException("Not found the film in Data Nase");
		}
		
		dao.delete(film);		
		
		return film;
	}

	@Override
	public Film getById(Long id) {
		
		return dao.getById(id);
	}
	
}
