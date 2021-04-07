package com.oliver.library.Application.Entities.Inventory;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Film extends RentalObject {
    public Film() {
        this.rentalPeriod = 7;
    }

    private String ageLimit;
    private String productionCountry;
    
}
