package com.oliver.library.Application.Components;

import com.oliver.library.Application.Entities.Abstract.Rental;
import com.oliver.library.Application.Entities.Inventory.Book;
import com.oliver.library.Application.Entities.User.Student;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Repositories.RentalObjectRepository;
import com.oliver.library.Application.Repositories.RentalRepository;
import com.oliver.library.Application.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Random;
import java.util.Set;

@Component
public class DataLoaderComponent implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentalObjectRepository rentalObjectRepository;

    @Autowired
    RentalRepository rentalRepository;

    public DataLoaderComponent() {
    }

    public void run(ApplicationArguments args) {
        Book b1 = new Book("book1", "horror", "1b", "scary book", Year.of(2019), "12093124", false, false);
        User u1 = new Student("jack", String.valueOf(new Random().nextDouble()), "test");
        Rental r1 = new Rental(b1, u1);

        rentalObjectRepository.save(b1);
        userRepository.save(u1);
        rentalRepository.save(r1);

        Set<User> jack = userRepository.findByName("jack");
        System.out.println(jack.size());
    }
}