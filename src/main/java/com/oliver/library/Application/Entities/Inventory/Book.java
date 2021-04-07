package com.oliver.library.Application.Entities.Inventory;


import javax.persistence.Entity;
import java.time.Year;

@Entity
public class Book extends RentalObject {
    private Year publicationYear;
    private String ISBN;

    public Year getPublicationYear() {
        return publicationYear;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isReference() {
        return reference;
    }

    public boolean isCourseLiterature() {
        return courseLiterature;
    }

    public Book(String title, String genre, String physicalLocation, String description, Year publicationYear, String ISBN, boolean reference, boolean courseLiterature) {
        super(title, genre, physicalLocation, description);
        this.publicationYear = publicationYear;
        this.ISBN = ISBN;
        this.reference = reference;
        this.courseLiterature = courseLiterature;
    }

    public Book() {

    }

    @Override
    public int getRentalPeriod() {
        if (this.reference || this.courseLiterature) return 0;
            // Assuming a month is 30 days.
        else return 30;
    }

    private boolean reference;
    private boolean courseLiterature;
}
