package com.bell.BellApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class BellApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BellApiApplication.class, args);
	}

}
