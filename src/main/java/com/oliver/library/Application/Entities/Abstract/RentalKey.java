package com.oliver.library.Application.Entities.Abstract;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

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
        return userId;
    }

    public String getRentalObjectId() {
        return rentalObjectId;
    }
}
