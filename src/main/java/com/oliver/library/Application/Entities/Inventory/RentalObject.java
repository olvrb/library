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
    @OneToMany(mappedBy = "user")
    private Set<Rental> rentals = new HashSet<>();

    public abstract int getRentalPeriod();


    private String genre;
    private String physicalLocation;
    private String description;

    @ManyToMany
    @JoinTable(name = "rental_object_collaborators", joinColumns = @JoinColumn(name = "rental_object_id"), inverseJoinColumns = @JoinColumn(name = "collaborators_id"))
    private Set<Collaborator> collaborators;


    public boolean canBeRented() {
        return this.getRentalPeriod() > 0;
    }
}
