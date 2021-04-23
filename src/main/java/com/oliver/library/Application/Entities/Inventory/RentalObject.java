package com.oliver.library.Application.Entities.Inventory;


import com.oliver.library.Application.Entities.Abstract.Collaborator;
import com.oliver.library.Application.Entities.BaseEntity;
import com.oliver.library.Application.Entities.Abstract.Rental;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class RentalObject extends BaseEntity {
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Rental> rentals = new HashSet<>();

    private String title;

    private String genre;

    private String physicalLocation;

    private String description;

    private String author;

    private boolean rented;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rental_object_collaborators", joinColumns = @JoinColumn(name = "rental_object_id"), inverseJoinColumns = @JoinColumn(name = "collaborators_id"))
    private Set<Collaborator> collaborators;

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

    public boolean canBeRented() {
        return this.getRentalPeriod() > 0 && !this.isRented();
    }

    public Set<Rental> getRentals() {
        return this.rentals;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isRented() {
        return this.rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
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

    public Set<Collaborator> getCollaborators() {
        return this.collaborators;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    public String getAuthor() {
        return this.author;
    }
}
