package com.oliver.library.Application.Entities.Inventory;

import javax.persistence.Entity;

@Entity
public class Journal extends RentalObject {

    @Override
    public int getRentalPeriod() {
        return 0;
    }
}
