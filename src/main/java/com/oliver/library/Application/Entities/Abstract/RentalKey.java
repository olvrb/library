package com.oliver.library.Application.Entities.Abstract;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// Combined primary key for Rental
@Embeddable
public class RentalKey implements Serializable {
    @SuppressWarnings("JpaDataSourceORMInspection")
    @Column(name = "user_id")
    private String userId;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @Column(name = "rental_object_id")
    private String rentalObjectId;

    public RentalKey(String rentalObjectId, String userId) {
        this.userId = userId;
        this.rentalObjectId = rentalObjectId;
    }

    public RentalKey() {
    }

    public String getUserId() {
        return this.userId;
    }

    public String getRentalObjectId() {
        return this.rentalObjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        RentalKey rentalKey = (RentalKey)o;
        return this.getUserId()
                   .equals(rentalKey.userId) && this.getRentalObjectId()
                                                    .equals(rentalKey.rentalObjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId(), this.getRentalObjectId());
    }
}
