package com.oliver.library.Application.Entities.User;


import com.oliver.library.Application.Entities.BaseEntity;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.Rental;

import javax.naming.Name;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


// A user has a

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User extends BaseEntity {
    public User(String name) {
        this.name = name;
    }
    private String name;
    protected int maxRent;

    @OneToMany
    private Set<Rental> rentals = new HashSet<>();

    @OneToMany
    private Set<User> supervisees = new HashSet<>();

    @ManyToOne
    private User supervisor;

    public User() {
        
    }


    public int getMaxRent() {
        return this.maxRent;
    }

    public Set<Rental> getRentals() {
        return this.rentals;
    }

    public boolean canRent(RentalObject object) {
        // Check if user is allowed to rent books, and if the object can be rented.
        return this.allowedToRent() && object.canBeRented();
    }

    public boolean allowedToRent() {
        // Check if user has reached max quota
        return this.maxRent < this.currentlyRented();
    }

    public String getName() {
        return name;
    }

    private int currentlyRented() {
        return this.rentals.stream()
                           .filter(x -> !x.returned())
                           .toArray().length;
    }
}
