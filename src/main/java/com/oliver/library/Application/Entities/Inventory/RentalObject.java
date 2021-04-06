package com.oliver.library.Application.Entities.Inventory;


import com.oliver.library.Application.Entities.BaseEntity;
import com.oliver.library.Application.Entities.Rental;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RentalObject extends BaseEntity {
    @OneToMany
    private Set<Rental> rentals = new HashSet<>();

    protected int rentalPeriod;


    public boolean canBeRented() {
        return this.rentalPeriod > 0;
    }
}
