package com.oliver.library.Application.Entities.Inventory;


import javax.persistence.Entity;
import java.time.Year;

@Entity
public class Book extends RentalObject {
    private String title;
    private Year publicationYear;
    private String ISBN;
    private boolean reference;
    private boolean courseLiterature;

    public Book() {
        if (this.reference || this.courseLiterature) this.rentalPeriod = 0;
            // Assuming a month is 30 days.
        else this.rentalPeriod = 30;
    }
}
