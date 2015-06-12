package com.mooreds.camel;


import org.apache.camel.spring.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mooreds.camel.routes.HelloWorldRoute;



public class App {
	
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	
	  public static void main(String[] args) throws Exception {
	        App app = new App();
	        app.boot();
	    }

	    public void boot() throws Exception {
	        // create a Main instance
	        Main main = new Main();
	        // enable hangup support so you can press ctrl + c to terminate the JVM
	        main.enableHangupSupport();
	        
	        // because we use this for beans.
	        main.setApplicationContextUri("classpath:META-INF/spring/camel-context.xml");
	        
	        // add all our routes.
	        main.addRouteBuilder(new HelloWorldRoute());

	        // run until you terminate the JVM
	        logger.info("Starting Camel. Use ctrl + c to terminate the JVM.\n");
	        main.run();
	    }

	
}
