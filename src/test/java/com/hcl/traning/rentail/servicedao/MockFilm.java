package com.hcl.traning.rentail.servicedao;

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

import com.hcl.traning.rentail.dao.impl.FilmDao;
import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.service.impl.FilmService;


public class MockFilm {

	@InjectMocks
	FilmService filmService;
		
	@Mock
	FilmDao filmDao;
		
	
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
		
		when(filmDao.getById(ID)).thenReturn(film);
		
		Film filmFound = filmService.getById(ID);
		
		assertEquals(filmFound.getId(), ID);
		assertEquals(filmFound.getTitle(), "Title");
		
		verify(filmDao, times(1)).getById(ID);
		
	}
	
	@Test
	public void testGetFilmAll() {
		
		Film film = new Film();
		film.setId(ID);
		film.setTitle("Title");
		
		List<Film> films = new ArrayList<Film>();
		films.add(film);
		
		when(filmDao.findAll()).thenReturn(films);
		
		List<Film> filmsFound = filmService.listAll();
		
		assertEquals(filmsFound.size(), 1);				
		verify(filmDao, times(1)).findAll();
		
	}
	
	@Test
	public void testDeleteFilmById() {
		
		Film film = new Film();
		film.setId(ID);
		film.setTitle("Title");
		
		when(filmDao.getById(ID)).thenReturn(film);
		
		filmService.delete(film.getId());					
		verify(filmDao, times(1)).delete(film);
		
	}
	
	@Test
	public void testSaveFilm() {
		final Long ID = 1l;
		
		Film film = new Film();
		film.setId(ID);
		film.setTitle("Title");
		
		filmService.add(film);
		
		verify(filmDao, times(1)).save(film);		
	}
		

}
