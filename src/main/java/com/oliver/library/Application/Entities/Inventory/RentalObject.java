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
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Rental> rentals = new HashSet<>();

    private String title;

    private String genre;

    private String physicalLocation;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rental_object_collaborators", joinColumns = @JoinColumn(name = "rental_object_id"), inverseJoinColumns = @JoinColumn(name = "collaborators_id"))
    private Set<Collaborator> collaborators;

    public RentalObject() {

    }

    public RentalObject(String title, String genre, String physicalLocation, String description) {
        this.title = title;
        this.genre = genre;
        this.physicalLocation = physicalLocation;
        this.description = description;
    }

    public abstract int getRentalPeriod();

    public boolean canBeRented() {
        return this.getRentalPeriod() > 0;
    }

    public Set<Rental> getRentals() {
        return rentals;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getPhysicalLocation() {
        return physicalLocation;
    }

    public String getDescription() {
        return description;
    }

    public Set<Collaborator> getCollaborators() {
        return collaborators;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
