package com.oliver.library;

import com.oliver.library.Application.GUI.LibraryApplicationGUI;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class LibraryApplication {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(LibraryApplication.class);

        // Allow for gui
        builder.headless(false);

        ConfigurableApplicationContext context = builder.run(args);
    }
}
