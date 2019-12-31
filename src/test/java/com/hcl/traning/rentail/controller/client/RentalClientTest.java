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
public class RentalClientTest {

	@Autowired
    private WebApplicationContext wac;
	 
    private MockMvc mvc;
    
    @Before
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }	
	
	@Test
	public void testGetData() throws Exception {		
		
		mvc.perform(MockMvcRequestBuilders.get("/api/rentals")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
			
	}
	
	@Test
	public void testGetDataRentalById() throws Exception {		
		
		mvc.perform(MockMvcRequestBuilders.get("/api/rentals/9999")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().is(404));
			
	}
	
	@Test
	public void testGetDataRentalFilms() throws Exception {		
		
		mvc.perform(MockMvcRequestBuilders.get("/api/rentals/9999/rentalfilms")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().is(404));
			
	}
	
	@Test
	public void testPostDataPayment() throws Exception {		
		
		mvc.perform(MockMvcRequestBuilders.post("/api/rentals/9999/payments")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print());
			
	}
	
	@Test
	public void testPostDataReturn() throws Exception {		
		
		mvc.perform(MockMvcRequestBuilders.post("/api/rentals/9999/return")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print());
			
	}	
	
	@Test
	public void testPostData() throws Exception {		
		
		mvc.perform(MockMvcRequestBuilders.post("/api/rentals/")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print());
			
	}
	
}
