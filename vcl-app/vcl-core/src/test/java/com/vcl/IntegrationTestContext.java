package com.vcl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
			{"classpath:/spring/spring-db-default-config.xml" , 
			"classpath:/spring/spring-test.xml" })
public class IntegrationTestContext {
	
}
