package com.oliver.library.Application.Entities.Inventory;


import javax.persistence.Entity;
import java.time.Year;

@Entity
public class Book extends RentalObject {
    private String title;
    private Year publicationYear;
    private String ISBN;

    @Override
    public int getRentalPeriod() {
        if (this.reference || this.courseLiterature) return 0;
            // Assuming a month is 30 days.
        else return 30;
    }

    private boolean reference;
    private boolean courseLiterature;
}
