package com.westpac.dda.spring.cfg;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {

		// Spring Context Bootstrapping
		servletContext.setInitParameter(ContextLoader.CONTEXT_CLASS_PARAM,
				AnnotationConfigWebApplicationContext.class.getName());
		servletContext.setInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, WestpacDdaStarterConfig.class.getName());
		servletContext.addListener(new ContextLoaderListener());

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WestpacDdaWebStarterConfig.class);

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("weather",
				new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

	}

}
