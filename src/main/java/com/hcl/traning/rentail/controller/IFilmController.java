package com.hcl.traning.rentail.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.traning.rentail.model.Film;

@RequestMapping("/api/films")
public interface IFilmController {

	@RequestMapping(value = "", method = RequestMethod.POST)
	Film postData(Film film);

	@RequestMapping(value = "", method = RequestMethod.GET)
	List<Film> getData();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	Film getById(Long id);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	Film deleteById(Long id);

}