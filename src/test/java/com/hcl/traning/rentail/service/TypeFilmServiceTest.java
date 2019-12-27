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

import com.hcl.traning.rentail.dao.TypeFilmRepository;
import com.hcl.traning.rentail.mapper.TypeFilmDto;
import com.hcl.traning.rentail.model.TypeFilm;
import com.hcl.traning.rentail.service.impl.TypeFilmService;


public class TypeFilmServiceTest {

	@InjectMocks
	TypeFilmService filmService;
		
	@Mock
	TypeFilmRepository filmDao;
	
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
	public void testGetAll() {
		
		TypeFilm film = new TypeFilm();
		film.setId(ID);
		film.setDaysToReturn(5);
		film.setPrice(40d);
		film.setName("Normal Rental");
		
		List<TypeFilm> films = new ArrayList<TypeFilm>();
		films.add(film);
		
		when(filmDao.findAll()).thenReturn(films);
		
		List<TypeFilmDto> filmsFound = filmService.getAll();
		
		assertEquals(filmsFound.size(), 1);				
		verify(filmDao, times(1)).findAll();
		
	}
	
	
	@Test
	public void testAdd() {
		final Long ID = 1l;
		ArgumentCaptor<TypeFilm> argumentAny = ArgumentCaptor.forClass(TypeFilm.class);
		
		TypeFilmDto film = new TypeFilmDto();
		film.setId(ID);
		film.setDaysToReturn(5);
		film.setPrice(40d);
		film.setName("Normal Rental");
						
		filmService.add(film);
		
		verify(filmDao).save(argumentAny.capture());		
	}
		

}
