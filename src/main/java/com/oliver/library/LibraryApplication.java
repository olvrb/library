package com.oliver.library;

import com.oliver.library.Application.GUI.LibraryApplicationGUI;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.oliver.library")
public class LibraryApplication {
    public LibraryApplication() {
        // Circular reference, tight coupling between control and ui.
        new LibraryApplicationGUI();
    }

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(LibraryApplication.class);

        builder.headless(false);

        ConfigurableApplicationContext context = builder.run(args);
    }

}
