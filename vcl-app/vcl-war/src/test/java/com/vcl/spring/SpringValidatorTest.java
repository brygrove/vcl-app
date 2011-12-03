package com.vcl.spring;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Validator Test 
 * used to check that all the spring mappings and hibernate mappings work. 
 * @author bgrove
 **/
public class SpringValidatorTest {
	
	
	@Test
	@Ignore
	public void testAppSpringMappingConfig(){
		//LogManager.shutdown();
		 
		new ClassPathXmlApplicationContext(new String[] { "/spring-test-db.xml", "/spring/spring-security.xml" });
	}

	
}
