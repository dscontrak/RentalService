package com.hcl.traning.rentail.service;

import java.util.Collection;
import java.util.List;

import com.hcl.traning.rentail.mapper.FilmDto;
import com.hcl.traning.rentail.model.Film;

public interface IFilmService {

	void add(FilmDto film);

	void addAll(Collection<Film> films);

	List<FilmDto> listAll();
	
	FilmDto delete(Long id);

	FilmDto getById(Long id);



}