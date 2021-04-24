package com.oliver.library.Application.Entities.Inventory;

import javax.persistence.Entity;

@Entity
public class Journal extends RentalObject {

    public Journal(String title, String genre, String physicalLocation, String description, String author) {
        super(title, genre, physicalLocation, description, author);
    }

    public Journal() {
    }

    @Override
    public int getRentalPeriod() {
        return 0;
    }
}
