package com.oliver.library.Application.Services;

import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Repositories.RentalObjectRepository;
import com.oliver.library.Application.Repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LibraryService {
    @Autowired
    private RentalObjectRepository rentalObjectRepository;

    public List<RentalObject> getAvailableRentalObjects(String searchString) {
        // return this.rentalObjectRepository.findByTitleContainingIgnoreCaseAndRentedFalse(searchString);
        List<RentalObject> searchResult = this.rentalObjectRepository.findByTitleContainingIgnoreCaseAndRentedFalse(
                searchString);
        searchResult.removeIf(x -> x.isRented());
        return searchResult;
    }

    public void save(RentalObject obj) {
        this.rentalObjectRepository.save(obj);
    }
}
