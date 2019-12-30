package com.hcl.traning.rentail.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.traning.rentail.controller.ITypeFilmController;
import com.hcl.traning.rentail.mapper.TypeFilmDto;
import com.hcl.traning.rentail.service.ITypeFilmService;

@RestController
public class TypeFilmController implements ITypeFilmController {

	@Autowired
	ITypeFilmService service;
	
	@Override
	public  TypeFilmDto postData(@RequestBody TypeFilmDto film) {
		service.add(film);
		return film;
	}

	@Override
	public List<TypeFilmDto> getData() {
		
		return service.getAll();
	}

	/*@Override
	public TypeFilmDto getById(Long id) {
		
		return null;
	}

	@Override
	public TypeFilmDto deleteById(Long id) {
		
		return null;
	}*/

}
