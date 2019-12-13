package com.hcl.traning.rentail.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.traning.rentail.controller.IFilmController;
import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.service.IFilmService;


@RestController
public class FilmController implements IFilmController {
	
	
	@Autowired
	IFilmService service;
	
	@Override	
	public Film postData(@RequestBody Film film) {
		
		service.add(film);		
		return film;
	}
	
	@Override	
	public List<Film> getData() {		
		
		return service.listAll();
	}
	
	@Override	
	public Film getById(@PathVariable("id") Long id){
		return service.getById(id);
	}
	
	@Override	
	public Film deleteById(@PathVariable("id") Long id){
		return service.delete(id);
	}
}
