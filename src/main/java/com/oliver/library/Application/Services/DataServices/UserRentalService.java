package com.oliver.library.Application.Services.DataServices;

import com.oliver.library.Application.Entities.Abstract.Rental;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Exceptions.InvalidLoanException;
import com.oliver.library.Application.Repositories.RentalObjectRepository;
import com.oliver.library.Application.Repositories.RentalRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserRentalService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RentalObjectRepository rentalObjectRepository;

    public Rental loan(User user, RentalObject object, Date startDate) throws InvalidLoanException {
        if (user.canRentOrReserve(object)) {
            Rental newRental = new Rental(object, user);
            if (startDate != null) newRental.setStartDate(startDate);

            this.rentalRepository.save(newRental);
            return newRental;
        } else throw new InvalidLoanException("Can not loan or reserve this object.");
    }

    public Rental loan(User user, RentalObject object) throws InvalidLoanException {
        return this.loan(user, object, null);
    }

    public Rental reserve(User user, RentalObject object, Date startDate) throws InvalidLoanException {
        return this.loan(user, object, startDate);
    }

    public Rental getCurrentRental(RentalObject object) {
        return object.getMostRecentRental();
    }

    public RentalObject getRentalObjectById(String id) {
        return this.rentalObjectRepository.findById(id)
                                          .orElse(null);
    }

    public RentalObject markRentalStatusForRentalObject(RentalObject obj, boolean status) throws NotFoundException {
        // Attempt to mark most recent Rental as returned.
        if (obj == null) throw new NotFoundException("Object not found");
        else {
            Rental r = obj.getMostRecentRental();
            if (r == null) throw new NotFoundException("No rentals to return");
            r.setReturned(status);
            this.rentalRepository.save(r);
        }
        return obj;
    }
}
