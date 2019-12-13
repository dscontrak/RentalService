package com.hcl.traning.rentail.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;

@Component
public class CodeGenerator{

	public int generateCustId() {
	        Random random = new Random();
	        return random.nextInt(100);
    }
		
	public String generate() throws HibernateException {
		String prefix = "RTL-";
		LocalDateTime dateToday = LocalDateTime.now();						
		
		return prefix + dateToday.format(DateTimeFormatter.ofPattern("yyyymmdd.hhmm-")) + generateCustId();
	}

}
