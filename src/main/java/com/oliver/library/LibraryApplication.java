package com.oliver.library;

import ch.qos.logback.classic.BasicConfigurator;
import com.oliver.library.Application.Entities.User.Employee;
import com.oliver.library.Application.Entities.User.Researcher;
import com.oliver.library.Application.Entities.User.Student;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Optional;
import java.util.Set;


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
                repository.save(new Student("jack"));

                Set<User> jack = repository.findByName("jack");
                System.out.println(jack.size());

            } catch (Exception e) {
                System.out.println(e);
            }
        };
    }


}
