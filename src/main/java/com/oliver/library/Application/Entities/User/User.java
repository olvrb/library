package com.oliver.library.Application.Entities.User;


import com.oliver.library.Application.Entities.Abstract.Rental;
import com.oliver.library.Application.Entities.BaseEntity;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.sun.istack.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
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
    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String ssn;

    @NotNull
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Rental> rentals = new HashSet<>();

    public User(String name, String ssn, String password) {
        this.name = name;
        this.ssn = ssn;
        this.setPassword(password);
    }

    public User() {

    }

    private static String hashPassword(String pw) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pw);
    }

    // Only require password to be longer than 8 chars.
    public static boolean validatePassword(String s) {
        return s.length() > 8;
    }

    // easy standard format
    public static boolean validateSsn(String s) {
        return s.length() == 10;
    }

    public abstract int getMaxRent();

    public boolean isAdmin() {
        return false;
    }

    // Get all rentals
    public Set<Rental> getRentals() {
        return this.rentals;
    }

    public Set<Rental> getCurrentRentals() {
        return this.getRentals()
                   .stream()
                   .filter(x -> !x.returned())
                   .collect(Collectors.toSet());
    }

    public boolean canRentOrReserve(RentalObject object) {
        // Check if user is allowed to rent books, and if the object can be rented.
        return this.allowedToRent() && object.canBeRentedOrReserved();
    }

    public boolean allowedToRent() {
        // Check if user has reached max quota
        return this.currentlyRented() <= this.getMaxRent();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int currentlyRented() {
        return this.getCurrentRentals()
                   .size();
    }

    public String getSsn() {
        return this.ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }
}
