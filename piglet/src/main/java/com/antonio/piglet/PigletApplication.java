package com.antonio.piglet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
//@EntityScan(basePackages = "com.antonio")
//@ComponentScan(basePackages = "com.antonio", excludeFilters = {
//		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ApplicationWorker.class),
//		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.antonio\\..*")
//})
public class PigletApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigletApplication.class, args);
	}

}
