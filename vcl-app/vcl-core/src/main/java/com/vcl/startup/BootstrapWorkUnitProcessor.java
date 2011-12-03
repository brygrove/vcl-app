package com.vcl.startup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BootstrapWorkUnitProcessor implements BeanPostProcessor {

	private static Log logger = LogFactory.getLog(BootstrapWorkUnitProcessor.class);
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		
		if (bean instanceof BootstrapWorkUnit) {
			BootstrapWorkUnit bootstrapWork = (BootstrapWorkUnit) bean;
			
			logger.info("executing bootstrap work unit : " + bootstrapWork.getClass().getSimpleName());
			bootstrapWork.execute();
		}
		
		return bean; 
	}

}
