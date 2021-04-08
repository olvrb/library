package com.oliver.library.Application.Entities.User;


import ch.qos.logback.classic.util.ContextSelectorStaticBinder;
import com.oliver.library.Application.Entities.BaseEntity;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.Abstract.Rental;
import com.oliver.library.Application.Repositories.UserRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


// Student has max 5 books
// Researcher has max 10 books
// Employee has max 7 books
// GeneralUser has max 3 books

// For efficiency and simplicity, all children are stored in a single table.


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User extends BaseEntity {
    @Autowired
    @Transient
    private static UserRepository userRepository;

    @NotNull
    private String name;

    @NotNull
    private String ssn;

    @NotNull
    private String password;

    @OneToMany(mappedBy = "rentalObject", fetch = FetchType.LAZY)
    private Set<Rental> rentals = new HashSet<>();


    public User(String name, String ssn, String password) {
        this.name = name;
        this.ssn = ssn;
        this.setPassword(password);
    }

    public User() {

    }

    public static User Authenticate(String ssn, String pw) {
        Optional<User> user = userRepository.findBySsn(ssn);

        // If user is not found, return null.
        // If user is found and password matches, return user.
        // Else return null.
        if (user.isEmpty()) {
            return null;
        } else if (user.isPresent() && new BCryptPasswordEncoder().matches(pw, user.get().password)) {
            return user.get();
        } else return null;
    }

    public abstract int getMaxRent();

    // Get all rentals
    public Set<Rental> getRentals() {
        return this.rentals;
    }

    public Set<Rental> getCurrentRentals() {
        return this.rentals.stream()
                           .filter(x -> !x.returned())
                           .collect(Collectors.toSet());
    }

    public boolean canRent(RentalObject object) {
        // Check if user is allowed to rent books, and if the object can be rented.
        return this.allowedToRent() && object.canBeRented();
    }

    public boolean allowedToRent() {
        // Check if user has reached max quota
        return this.getMaxRent() < this.currentlyRented();
    }

    public String getName() {
        return name;
    }

    public int currentlyRented() {
        return this.getCurrentRentals()
                   .size();
    }

    public String getSsn() {
        return ssn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    private String hashPassword(String pw) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pw);
    }
}
