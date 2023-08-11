package com.fabrick.test;

import jakarta.persistence.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.fabrick")
@EntityScan("com.fabrick.test.entity")
public class TestApplication {

	private static final Logger log = LoggerFactory.getLogger(TestApplication.class);


	public static void main(String[] args){
		log.info("test application booting");
		SpringApplication.run(TestApplication.class, args);
	}



}
