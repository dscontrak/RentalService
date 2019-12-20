package com.hcl.traning.rentail.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.traning.rentail.mapper.FilmDto;

@RequestMapping("/api/films")
public interface IFilmController {

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
	FilmDto postData(FilmDto film);

	@RequestMapping(value = "", method = RequestMethod.GET)
	List<FilmDto> getData();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	FilmDto getById(Long id);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	FilmDto deleteById(Long id);

}