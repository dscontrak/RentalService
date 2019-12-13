package com.hcl.traning.rentail.dao;

import java.util.List;
import java.util.Set;

import com.hcl.traning.rentail.model.Film;

public interface IFilmDao {

	void save(Film film);

	void update(Film film);

	void updateAll(Set<Film> films);
	
	void delete(Film film);

	List<Film> findAll();

	Film getById(Long id);

}