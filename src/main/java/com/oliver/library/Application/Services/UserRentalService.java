package com.oliver.library.Application.Services;

import com.oliver.library.Application.Entities.Abstract.Rental;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Exceptions.InvalidLoanException;
import com.oliver.library.Application.Repositories.RentalObjectRepository;
import com.oliver.library.Application.Repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRentalService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RentalObjectRepository rentalObjectRepository;

    // TODO: Mark rentalObject as rented
    public Rental loan(User user, RentalObject object) throws InvalidLoanException {
        if (user.canRent(object)) {
            Rental newRental = new Rental(object, user);
            // object.setRented(true);
            this.rentalRepository.save(newRental);
            // this.rentalObjectRepository.save(object);
            return newRental;
        } else throw new InvalidLoanException("Can not loan this object.");
    }
}
