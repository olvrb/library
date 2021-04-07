package com.oliver.library.Application.Entities.User;


import com.oliver.library.Application.Entities.BaseEntity;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.Abstract.Rental;

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
    public User(String name) {
        this.name = name;
    }

    private String name;
    private String ssn;
    private String password;

    @OneToMany(mappedBy = "rentalObject")
    private Set<Rental> rentals = new HashSet<>();


    public User() {

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

    // TODO: Implement
    private String hashPassword(String pw) {
        return pw;
    }
}
