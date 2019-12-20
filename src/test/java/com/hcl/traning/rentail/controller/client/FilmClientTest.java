package com.hcl.traning.rentail.controller.client;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-servlet.xml")
public class FilmClientTest {

	@Autowired
    private WebApplicationContext wac;
	 
    private MockMvc mvc;
    
    @Before
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
	@Test
	public void getDataTest() throws Exception {		
		
		mvc.perform(MockMvcRequestBuilders.get("/api/films")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
			
	}
		
	
}
