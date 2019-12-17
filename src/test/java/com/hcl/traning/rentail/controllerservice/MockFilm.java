package com.hcl.traning.rentail.controllerservice;

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
import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.service.impl.FilmService;


public class MockFilm {

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
	public void testGetFilmById() {
		
		Film film = new Film();
		film.setId(ID);
		film.setTitle("Title");
		
		when(filmService.getById(ID)).thenReturn(film);
		
		Film filmFound = filmService.getById(ID);
		
		assertEquals(filmFound.getId(), ID);
		assertEquals(filmFound.getTitle(), "Title");
		
		verify(filmService, times(1)).getById(ID);
		
	}
	
	@Test
	public void testGetFilmAll() {
		
		Film film = new Film();
		film.setId(ID);
		film.setTitle("Title");
		
		List<Film> films = new ArrayList<Film>();
		films.add(film);
		
		when(filmService.listAll()).thenReturn(films);
		
		List<Film> filmsFound = filmService.listAll();
		
		assertEquals(filmsFound.size(), 1);				
		verify(filmService, times(1)).listAll();
		
	}
	
	@Test
	public void testDeleteFilmById() {
		
		Film film = new Film();
		film.setId(ID);
		film.setTitle("Title");
		
		when(filmService.getById(ID)).thenReturn(film);
		
		filmService.delete(film.getId());					
		verify(filmService, times(1)).delete(film.getId());
		
	}
	
	@Test
	public void testSaveFilm() {
		final Long ID = 1l;
		
		Film film = new Film();
		film.setId(ID);
		film.setTitle("Title");
		
		filmService.add(film);
		
		verify(filmService, times(1)).add(film);		
	}
		

}
