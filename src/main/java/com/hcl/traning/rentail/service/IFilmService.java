package com.hcl.traning.rentail.service;

import java.util.List;

import com.hcl.traning.rentail.mapper.FilmDto;

public interface IFilmService {

	void add(FilmDto film);

	//void addAll(Collection<Film> films);

	List<FilmDto> listAll();
	
	FilmDto delete(Long id);

	FilmDto getById(Long id);



}