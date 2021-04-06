package com.oliver.library.Application.Entities.Inventory;

import javax.persistence.Entity;

@Entity
public class Film extends RentalObject {
    public Film() {
        this.rentalPeriod = 7;
    }
}
