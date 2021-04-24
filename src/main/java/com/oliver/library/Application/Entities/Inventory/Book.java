package com.oliver.library.Application.Entities.Inventory;


import javax.persistence.Entity;
import java.time.Year;

@Entity
public class Book extends RentalObject {
    private Year publicationYear;

    private String ISBN;

    private boolean reference;

    private boolean courseLiterature;

    public Book(String title, String genre, String physicalLocation, String description, Year publicationYear, String ISBN, String author, boolean reference, boolean courseLiterature) {
        super(title, genre, physicalLocation, description, author);
        this.publicationYear = publicationYear;
        this.ISBN = ISBN;
        this.reference = reference;
        this.courseLiterature = courseLiterature;
    }

    public Book() {

    }

    public static boolean validateBook(Book book) {
        return true;
    }

    public Year getPublicationYear() {
        return this.publicationYear;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public boolean isReference() {
        return this.reference;
    }

    public boolean isCourseLiterature() {
        return this.courseLiterature;
    }

    // If book is reference or course literature, can't rent at all. If other book, can rent for 30 days.
    @Override
    public int getRentalPeriod() {
        if (this.reference || this.courseLiterature) return 0;
            // Assuming a month is 30 days.
        else return 30;
    }
}
