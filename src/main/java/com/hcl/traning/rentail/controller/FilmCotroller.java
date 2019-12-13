package com.hcl.traning.rentail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.service.IFilmService;


@RestController
@RequestMapping("/api/films")
public class FilmCotroller {
	
	@Autowired
	IFilmService service;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Film postData(@RequestBody Film film) {
		
		service.add(film);		
		return film;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Film> getData() {		
		
		return service.listAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Film getById(@PathVariable("id") Long id){
		return service.getById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Film deleteById(@PathVariable("id") Long id){
		return service.delete(id);
	}
}
