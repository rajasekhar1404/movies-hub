package com.movies_hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MoviesHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesHubApplication.class, args);
	}

}
