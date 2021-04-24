package com.oliver.library.Application.Repositories;

import com.oliver.library.Application.Entities.Abstract.Rental;
import com.oliver.library.Application.Entities.Abstract.RentalKey;
import org.springframework.data.repository.CrudRepository;

public interface RentalRepository extends CrudRepository<Rental, RentalKey> {
    Rental findByIdRentalObjectIdAndIdUserId(String rentalObjectId, String userId);

    Rental findByIdRentalObjectId(String rentalObjectId);

    Rental findByReturnedFalse();
}
