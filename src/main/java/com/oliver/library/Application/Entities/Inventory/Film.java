package com.oliver.library.Application.Entities.Inventory;

import javax.persistence.Entity;

@Entity
public class Film extends RentalObject {

    private String ageLimit;

    private String productionCountry;

    public Film(String title, String genre, String physicalLocation, String description, String author, String ageLimit, String productionCountry) {
        super(title, genre, physicalLocation, description, author);
        this.ageLimit = ageLimit;
        this.productionCountry = productionCountry;
    }

    public Film() {

    }

    @Override
    public int getRentalPeriod() {
        return 7;
    }

    public String getAgeLimit() {
        return this.ageLimit;
    }

    public String getProductionCountry() {
        return this.productionCountry;
    }
}
