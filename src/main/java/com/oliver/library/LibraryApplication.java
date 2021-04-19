package com.oliver.library;

import com.oliver.library.Application.Entities.User.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.oliver.library")
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
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean signedIn() {
        return this.currentUser != null;
    }
}
