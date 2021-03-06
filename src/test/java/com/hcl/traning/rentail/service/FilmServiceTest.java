package com.hcl.traning.rentail.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.hcl.traning.rentail.dao.FilmRepository;
import com.hcl.traning.rentail.mapper.FilmDto;
import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.service.impl.FilmService;


public class FilmServiceTest {

	@InjectMocks
	FilmService filmService;
		
	@Mock
	FilmRepository filmDao;
	
	@Spy
	DozerBeanMapper mapper;
		
	
	final Long ID = 60l;
	
	@Before 
	public void init()
    {
        MockitoAnnotations.initMocks(this);
        List<String> mappingFiles = new ArrayList<String>();
		mappingFiles.add("dozerJdk8Converters.xml");
		
		mapper = new DozerBeanMapper();	    
		mapper.setMappingFiles(mappingFiles);
    }
	
	@Test
	public void testGetById() {
		
		Film film = new Film();
		film.setId(ID);
		film.setTitle("Title");
		
					
		when(filmDao.findOne(ID)).thenReturn(film);		
		FilmDto filmFound = filmService.getById(ID);
		
		assertEquals(filmFound.getId(), ID);
		assertEquals(filmFound.getTitle(), "Title");
		
		verify(filmDao, times(1)).findOne(ID);
		
	}
	
	@Test
	public void testListAll() {
		
		Film film = new Film();
		film.setId(ID);
		film.setTitle("Title");
		
		List<Film> films = new ArrayList<Film>();
		films.add(film);
		
		when(filmDao.findAll()).thenReturn(films);
		
		List<FilmDto> filmsFound = filmService.listAll();
		
		assertEquals(filmsFound.size(), 1);				
		verify(filmDao, times(1)).findAll();
		
	}
	
	@Test
	public void testDelete() {
		
		Film film = new Film();
		film.setId(ID);
		film.setTitle("Title");
		
				
		when(filmDao.findOne(ID)).thenReturn(film);
		
		filmService.delete(film.getId());					
		verify(filmDao, times(1)).delete(film);
		
	}
	
	@Test
	public void testAdd() {
		final Long ID = 1l;
		ArgumentCaptor<Film> argumentAny = ArgumentCaptor.forClass(Film.class);
		
		FilmDto film = new FilmDto();
		film.setId(ID);
		film.setTitle("Title");
						
		filmService.add(film);
		
		verify(filmDao).save(argumentAny.capture());		
	}
		

}
