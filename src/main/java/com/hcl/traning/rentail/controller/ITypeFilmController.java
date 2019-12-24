package com.hcl.traning.rentail.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.traning.rentail.mapper.TypeFilmDto;

@RequestMapping("/api/typefilms")
public interface ITypeFilmController {
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
	TypeFilmDto postData(TypeFilmDto film);

	@RequestMapping(value = "", method = RequestMethod.GET)
	List<TypeFilmDto> getData();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	TypeFilmDto getById(Long id);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	TypeFilmDto deleteById(Long id);
}
