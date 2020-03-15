package com.rest.webservices.restfulwebservices.controller;

import java.util.Locale;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservices.restfulwebservices.mbean.HelloWorldBean;

@RestController
public class HelloWorldContainer {
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method = RequestMethod.GET,path = "hello-world")
	public String getString() {
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping(path = "hello-world-bean")
	public HelloWorldBean getHelloWorldBeanString() {
		System.out.println(new HelloWorldBean("Hello World"));
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path = "hello-world/path-variable/{name}")
	public HelloWorldBean getHelloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}

}
