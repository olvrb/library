package com.oliver.library.Application.Services.DataServices;

import com.oliver.library.Application.Entities.Abstract.Rental;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Repositories.RentalObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<RentalObject> getUnreturnedRentalObjects() {
        List<RentalObject> objs = (List<RentalObject>)this.rentalObjectRepository.findAll();

        // Filter objects that aren't currently rented, as well as objects that are past their due dates.
        return objs.stream()
                   .filter(x -> x.isRented())
                   .filter(x -> x.getMostRecentRental()
                                 .getReturnDate()
                                 .before(Calendar.getInstance()
                                                 .getTime()))
                   .collect(Collectors.toList());
    }

    public void save(RentalObject obj) {
        this.rentalObjectRepository.save(obj);
    }
}
