package com.oliver.library;

import ch.qos.logback.classic.BasicConfigurator;
import com.oliver.library.Application.Entities.User.Employee;
import com.oliver.library.Application.Entities.User.Researcher;
import com.oliver.library.Application.Entities.User.Student;
import com.oliver.library.Application.Repositories.UserRepository;
import org.activejpa.enhancer.ActiveJpaAgent;
import org.activejpa.enhancer.ActiveJpaAgentLoader;
import org.activejpa.jpa.JPA;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;


@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			// save a few customers
			try {
				repository.save(new Researcher());

			} catch (Exception e) {
				System.out.println(e);
			}
		};
	}


}