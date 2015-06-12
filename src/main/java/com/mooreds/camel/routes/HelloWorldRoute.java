package com.mooreds.camel.routes;

import org.apache.camel.builder.RouteBuilder;

public class HelloWorldRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:hello").log("hello")
		.process(exchange -> {
					exchange.getIn().setBody("hello");
				});

	}

}
