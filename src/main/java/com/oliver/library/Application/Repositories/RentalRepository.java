package com.oliver.library.Application.Repositories;

import com.oliver.library.Application.Entities.Abstract.Rental;
import com.oliver.library.Application.Entities.Abstract.RentalKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

// Define rental transactions
public interface RentalRepository extends CrudRepository<Rental, RentalKey> {
    Rental findByIdRentalObjectIdAndIdUserId(String rentalObjectId, String userId);

    Rental findByIdRentalObjectId(String rentalObjectId);

    Rental findByReturnedFalse();

    @Modifying(clearAutomatically = true)
    Rental save(Rental r);

    Set<Rental> findByIdUserId(String id);
}
