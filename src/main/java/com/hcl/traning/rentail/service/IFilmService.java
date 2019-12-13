package com.hcl.traning.rentail.service;

import java.util.Collection;
import java.util.List;

import com.hcl.traning.rentail.model.Film;

public interface IFilmService {

	void add(Film film);

	void addAll(Collection<Film> films);

	List<Film> listAll();
	
	Film delete(Long id);

	Film getById(Long id);



}