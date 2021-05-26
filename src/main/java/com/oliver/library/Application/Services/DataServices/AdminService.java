package com.oliver.library.Application.Services.DataServices;

import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Exceptions.RentalObjectRentedException;
import com.oliver.library.Application.Repositories.RentalObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdminService {
    @Autowired
    private RentalObjectRepository rentalObjectRepository;

    @Transactional
    public void removeRentalObject(RentalObject object) throws RentalObjectRentedException {
        // Try and delete object
        try {
            this.rentalObjectRepository.delete(object);
        } catch (Exception e) {
            throw new RentalObjectRentedException("Object currently rented: cannot delete.");
        }
    }

    public void addRentalObject() {

    }
}
