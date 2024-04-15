package com.revature.Project1Stars;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.revature.models")
@ComponentScan("com.revature")
@EnableJpaRepositories("com.revature.daos")
@SpringBootApplication
public class Project1StarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project1StarsApplication.class, args);
	}

}
