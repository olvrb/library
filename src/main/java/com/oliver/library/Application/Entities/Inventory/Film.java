package com.oliver.library.Application.Entities.Inventory;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Film extends RentalObject {

    private String ageLimit;
    private String productionCountry;

    @Override
    public int getRentalPeriod() {
        return 7;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public String getProductionCountry() {
        return productionCountry;
    }
}
