package com.oliver.library.Application.Entities.Inventory;


import com.oliver.library.Application.Entities.Abstract.Rental;
import com.oliver.library.Application.Entities.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class RentalObject extends BaseEntity {
    @SuppressWarnings("unused")
    @OneToMany(mappedBy = "rentalObject", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Rental> rentals;

    private String title;

    private String genre;

    private String physicalLocation;

    private String description;

    private String author;

    public RentalObject() {

    }

    public RentalObject(String title, String genre, String physicalLocation, String description, String author) {
        this.title = title;
        this.genre = genre;
        this.physicalLocation = physicalLocation;
        this.description = description;
        this.author = author;
    }

    public abstract int getRentalPeriod();

    public boolean canBeRentedOrReserved() {
        return this.getRentalPeriod() > 0;
    }

    public Set<Rental> getRentals() {
        return this.rentals;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isRented() {
        // If RentalObject has at least one unreturned rental, it's deemed rented.
        return this.getMostRecentRental() != null;
    }

    // Find the first Rental that is currently not returned.
    public Rental getMostRecentRental() {
        for (Rental r : this.getRentals()) {
            if (!r.returned()) return r;
        }
        return null;
    }

    // Get next date this object can be rented/reserved
    public Date getNextRentDate() {
        return this.getMostRecentRental()
                   .getReturnDate();
    }

    public String getGenre() {
        return this.genre;
    }

    public String getPhysicalLocation() {
        return this.physicalLocation;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.isRented() ? String.format("%s (rented)", this.getTitle()) : this.getTitle();
    }

    public String getAuthor() {
        return this.author;
    }
}
