package com.oliver.library.Application.Repositories;

import com.oliver.library.Application.Entities.Inventory.Book;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// Define rentalObject transactions
public interface RentalObjectRepository extends CrudRepository<RentalObject, String> {
    List<RentalObject> findByTitleContainingIgnoreCase(String title);

    List<RentalObject> findByAuthorContainingIgnoreCase(String collaboratorName);

    List<Book> findByISBN(String ISBN);

    List<RentalObject> findByGenreContainingIgnoreCase(String genre);
}
