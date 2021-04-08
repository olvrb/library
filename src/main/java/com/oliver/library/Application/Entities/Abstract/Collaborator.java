package com.oliver.library.Application.Entities.Abstract;

import com.oliver.library.Application.Entities.BaseEntity;
import com.oliver.library.Application.Entities.Inventory.RentalObject;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Collaborator extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "collaborators", fetch = FetchType.EAGER)
    private Set<RentalObject> rentalObjects;

    public Collaborator(String name) {
        this.name = name;
    }

    public Collaborator() {

    }

    public String getName() {
        return name;
    }

    public Set<RentalObject> getRentalObjects() {
        return rentalObjects;
    }
}
