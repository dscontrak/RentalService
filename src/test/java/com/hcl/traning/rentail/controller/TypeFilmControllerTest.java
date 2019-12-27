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

import com.hcl.traning.rentail.controller.impl.TypeFilmController;
import com.hcl.traning.rentail.mapper.TypeFilmDto;
import com.hcl.traning.rentail.service.ITypeFilmService;

public class TypeFilmControllerTest {

	@InjectMocks
	TypeFilmController typeFilmController;
	 
	@Mock
	ITypeFilmService service;
	
	final Long ID = 1l;
	
	@Before 
	public void init()
    {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void  testPostData() {
		TypeFilmDto film = new TypeFilmDto();
				
		typeFilmController.postData(film);
		verify(service,times(1)).add(film);
	}

	
	@Test
	public void getData(){
		TypeFilmDto film = new TypeFilmDto();
		List<TypeFilmDto> films = new ArrayList<TypeFilmDto>(); 
		films.add(film);
		
		when(service.getAll()).thenReturn(films);
		
		List<TypeFilmDto> filmsFound =  typeFilmController.getData();
		
		verify(service, times(1)).getAll();
		assertEquals(filmsFound.size(), 1);
		
	}

	
	
	
}
