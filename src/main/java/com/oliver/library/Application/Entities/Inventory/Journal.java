package com.oliver.library.Application.Entities.Inventory;

import javax.persistence.Entity;

@Entity
public class Journal extends RentalObject {
    public Journal() {
        this.rentalPeriod = 0;
    }
}
