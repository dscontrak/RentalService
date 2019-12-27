package com.hcl.traning.rentail.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.traning.rentail.dao.FilmRepository;
import com.hcl.traning.rentail.mapper.FilmDto;
import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.service.IFilmService;

@Service
public class FilmService implements IFilmService {
	
	@Autowired
	private FilmRepository dao;	
	
	@Autowired
	@Qualifier("org.dozer.Mapper")
	Mapper mapper;
	
	
	@Override
	public void add(FilmDto filmDto) {
		
		Film film = mapper.map(filmDto, Film.class);
		
		LocalDateTime timestamp = LocalDateTime.now();
		film.setUpdated(timestamp);
		film.setCreated(timestamp);		
		
		dao.save(film);
		filmDto.setId(film.getId());
	}	
	
	/*@Override
	public void addAll(Collection<Film> films) {
		LocalDateTime timestamp = LocalDateTime.now();
		
		films.forEach(f ->{
			f.setUpdated(timestamp);
			f.setCreated(timestamp);
			dao.save(f);
		});
				
	}*/
	
	@Override
	@Transactional(readOnly = true)
	public List<FilmDto> listAll() {
		
		List<FilmDto> films = new ArrayList<FilmDto>();
		dao.findAll().forEach(f -> {
			films.add(mapper.map(f, FilmDto.class));
		});
		
		return films;

	}

	@Override
	public FilmDto delete(Long id) {
		
		Film film = dao.findOne(id);
		
		
		if(film == null) {
			throw new IllegalArgumentException("Not found the film in Data Nase");
		}
					
		dao.delete(film);		
		
		return mapper.map(film, FilmDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public FilmDto getById(Long id) {
				
		Film film = dao.findOne(id);					
		return mapper.map(film, FilmDto.class);
	}
	
}
