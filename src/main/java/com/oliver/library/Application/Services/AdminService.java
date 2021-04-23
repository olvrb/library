package com.oliver.library.Application.Services;

import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Repositories.RentalObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private RentalObjectRepository rentalObjectRepository;

    public void removeRentalObject(RentalObject object) {
        this.rentalObjectRepository.delete(object);
    }

    public void addRentalObject() {

    }
}
