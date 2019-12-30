package com.hcl.traning.rentail.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hcl.traning.rentail.dao.TypeFilmRepository;
import com.hcl.traning.rentail.mapper.TypeFilmDto;
import com.hcl.traning.rentail.model.TypeFilm;
import com.hcl.traning.rentail.service.ITypeFilmService;

@Service
public class TypeFilmService implements ITypeFilmService {

	@Autowired
	TypeFilmRepository dao;
	
	@Autowired
	@Qualifier("org.dozer.Mapper")
	Mapper mapper;
	
	@Override
	public void add(TypeFilmDto film) {		
		TypeFilm typeFilm;
		typeFilm = mapper.map(film, TypeFilm.class);
		dao.save(typeFilm);
		
		film.setId(typeFilm.getId());
	}

	@Override
	public List<TypeFilmDto> getAll() {
		
		List<TypeFilm> types = dao.findAll();
		List<TypeFilmDto> typesDto = new ArrayList<TypeFilmDto>();
		
		types.forEach(t -> {
			typesDto.add(mapper.map(t, TypeFilmDto.class));
		});
		
		return typesDto;
	}

}
