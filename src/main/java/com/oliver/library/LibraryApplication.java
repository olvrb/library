package com.oliver.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner demo(UserRepository userRepository, RentalObjectRepository rentalObjectRepository, RentalRepository rentalRepository) {
        return (args) -> {
            // save a few customers
            try {
                Book b1 = new Book("book1", "horror", "1b", "scary book", Year.of(2019), "12093124", false, false);
                User u1 = new Student("jack", "0204150072", "test");
                Rental r1 = new Rental(b1, u1);

                rentalObjectRepository.save(b1);
                userRepository.save(u1);
                rentalRepository.save(r1);

                Set<User> jack = userRepository.findByName("jack");
                System.out.println(jack.size());

            } catch (Exception e) {
                System.out.println(e);
            }
        };
    }*/

}
