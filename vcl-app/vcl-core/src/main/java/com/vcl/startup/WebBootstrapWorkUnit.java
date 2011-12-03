package com.vcl.startup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

public abstract class WebBootstrapWorkUnit implements BootstrapWorkUnit, ApplicationContextAware {

	private static Log logger = LogFactory.getLog(WebBootstrapWorkUnit.class);

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void execute() {
		if (applicationContext instanceof WebApplicationContext) {
			executeWork();
		} else {
			logger.info("skipping the execution non web-based bootstrap unit of work : " 
					+ getClass().getSimpleName());
		}
	}

	public abstract void executeWork();
}
