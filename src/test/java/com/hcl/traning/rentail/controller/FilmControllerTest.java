package com.hcl.traning.rentail.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hcl.traning.rentail.controller.impl.FilmController;
import com.hcl.traning.rentail.mapper.FilmDto;
import com.hcl.traning.rentail.service.impl.FilmService;


public class FilmControllerTest {

	@Mock
	FilmService filmService;
		
	@InjectMocks
	FilmController filmCtrl;
		
	
	final Long ID = 60l;
	
	@Before 
	public void init()
    {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetById() {
		
		FilmDto film = new FilmDto();
		film.setId(ID);
		film.setTitle("Title");
		
		when(filmService.getById(ID)).thenReturn(film);
		
		FilmDto filmFound = filmService.getById(ID);
		
		assertEquals(filmFound.getId(), ID);
		assertEquals(filmFound.getTitle(), "Title");
		
		verify(filmService, times(1)).getById(ID);
		
	}
	
	@Test
	public void testGetData() {
		
		FilmDto film = new FilmDto();
		film.setId(ID);
		film.setTitle("Title");
		
		List<FilmDto> films = new ArrayList<FilmDto>();
		films.add(film);
		
		when(filmService.listAll()).thenReturn(films);
		
		List<FilmDto> filmsFound = filmService.listAll();
		
		assertEquals(filmsFound.size(), 1);				
		verify(filmService, times(1)).listAll();
		
	}
	
	@Test
	public void testDeleteById() {
		
		FilmDto film = new FilmDto();
		film.setId(ID);
		film.setTitle("Title");
		
		when(filmService.getById(ID)).thenReturn(film);
		
		filmService.delete(film.getId());					
		verify(filmService, times(1)).delete(film.getId());
		
	}
	
	@Test
	public void testPostData() {
		final Long ID = 1l;
		
		FilmDto film = new FilmDto();
		film.setId(ID);
		film.setTitle("Title");
		
		filmService.add(film);
		
		verify(filmService, times(1)).add(film);		
	}
		

}
