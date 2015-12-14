package com.pduleba.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.spring.controller.SpringController;

public class Main {

	public static final Logger LOG = LoggerFactory.getLogger(Main.class);
	private SpringController controller;
	
	public static void main(String[] args) {
		new Main().run();
	}

	public Main() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		this.controller = ctx.getBean(SpringController.class);
	}
	
	private void run() {
		LOG.info("Starting...");
		
		this.controller.doAction();
		
		LOG.info("Complete");
	}
	
}
