package com.oliver.library.Application.Entities.Abstract;

import com.oliver.library.Application.Entities.BaseEntity;
import com.oliver.library.Application.Entities.Inventory.RentalObject;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Collaborator extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "collaborators")
    private Set<RentalObject> rentalObjects;
}
