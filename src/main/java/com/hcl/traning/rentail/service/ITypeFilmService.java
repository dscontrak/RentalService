package com.hcl.traning.rentail.service;

import java.util.List;

import com.hcl.traning.rentail.mapper.TypeFilmDto;

public interface ITypeFilmService {
	void add(TypeFilmDto film);
	
	List<TypeFilmDto> getAll();
}
