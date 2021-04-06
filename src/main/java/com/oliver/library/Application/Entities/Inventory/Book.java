package com.oliver.library.Application.Entities.Inventory;


import javax.persistence.Entity;

@Entity
public class Book extends RentalObject {
    private boolean reference;
    private boolean courseLiterature;

    public Book() {
        if (this.reference || this.courseLiterature) this.rentalPeriod = 0;
            // Assuming a month is 30 days.
        else this.rentalPeriod = 30;
    }
}
