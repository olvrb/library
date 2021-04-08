package com.oliver.library;

import com.oliver.library.Application.Entities.User.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class LibraryApplication {

    private User currentUser;

    private LibraryApplicationGUI gui;

    public LibraryApplication() {
        // Circular reference, tight coupling between control and ui.
        this.gui = new LibraryApplicationGUI(this);
    }

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(LibraryApplication.class);

        builder.headless(false);

        ConfigurableApplicationContext context = builder.run(args);

        SpringApplication.run(LibraryApplication.class, args);
    }
}
