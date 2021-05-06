package com.oliver.library.Application.Services;

import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Repositories.RentalObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {
    @Autowired
    private RentalObjectRepository rentalObjectRepository;

    public List<RentalObject> getRentalObjectsByTitle(String searchString) {
        return this.rentalObjectRepository.findByTitleContainingIgnoreCase(searchString);
    }

    public List<RentalObject> getRentalObjectsByAuthor(String searchString) {
        return this.rentalObjectRepository.findByAuthorContainingIgnoreCase(searchString);
    }

    public List<RentalObject> getRentalObjectsByIsbn(String searchString) {
        // Turn List<Book> into a List<RentalObject>.
        return new ArrayList<>(this.rentalObjectRepository.findByISBN(searchString));
    }

    public List<RentalObject> getRentalObjectsByGenre(String searchString) {
        return this.rentalObjectRepository.findByGenreContainingIgnoreCase(searchString);
    }

    public void save(RentalObject obj) {
        this.rentalObjectRepository.save(obj);
    }
}
