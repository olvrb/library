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
    @OneToMany(mappedBy = "rentalObject", fetch = FetchType.EAGER)
    private Set<Rental> rentals;

    private String title;

    private String genre;

    private String physicalLocation;

    private String description;

    private String author;

    //    @ManyToMany(fetch = FetchType.EAGER)
    //    @JoinTable(name = "rental_object_collaborators", joinColumns = @JoinColumn(name = "rental_object_id"), inverseJoinColumns = @JoinColumn(name = "collaborators_id"))
    //    private Set<Collaborator> collaborators;

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
        return this.getCurrentRental() != null;
    }

    // Find the first Rental that is currently not returned.
    public Rental getCurrentRental() {
        for (Rental r : this.getRentals()) {
            if (!r.returned()) return r;
        }
        return null;
    }

    public Date getNextRentDate() {
        return this.getCurrentRental()
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

    //    public Set<Collaborator> getCollaborators() {
    //        return this.collaborators;
    //    }

    @Override
    public String toString() {
        return this.isRented() ? String.format("%s (rented)", this.getTitle()) : this.getTitle();
    }

    public String getAuthor() {
        return this.author;
    }
}
