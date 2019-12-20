package com.hcl.traning.rentail;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {
	
	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean() {	   
		List<String> mappingFiles = new ArrayList<String>();
		mappingFiles.add("dozerJdk8Converters.xml");
		
		DozerBeanMapper dozerBean = new DozerBeanMapper();	    
		dozerBean.setMappingFiles(mappingFiles);
	    return dozerBean;
	}

}
